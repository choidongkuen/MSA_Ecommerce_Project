package com.example.service.customer;

import com.example.domain.entity.CustomerBalanceHistory;
import com.example.domain.repository.CustomerBalanceHistoryRepository;
import com.example.domain.repository.CustomerRepository;
import com.example.dto.ChangeBalanceDto;
import com.example.exception.customer.CustomerBalanceNotEnoughException;
import com.example.exception.customer.CustomerNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerBalanceHistoryService {

    private final CustomerBalanceHistoryRepository customerBalanceHistoryRepository;

    private final CustomerRepository customerRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public CustomerBalanceHistory changeBalance(Long customerId, ChangeBalanceDto request) {

        CustomerBalanceHistory preCustomerBalanceHistory
                = this.customerBalanceHistoryRepository.findFirstByCustomer_IdOrderByIdDesc(customerId) // 가장 최신 기록
                            .orElse(CustomerBalanceHistory.builder()
                                    .changedBalance(0)
                                    .currentBalance(0)
                                    .customer(
                                            customerRepository.findById(customerId)
                                                    .orElseThrow(()-> new CustomerNotExistException("일치하는 회원이 존재하지 않습니다."))
                                    )
                                    .build());

        if(preCustomerBalanceHistory.getCurrentBalance() + request.getMoney() < 0) {
            throw new CustomerBalanceNotEnoughException("회원의 통장이 마이너스입니다.");
        } // 변경 금액이 마이너스인 경우

        preCustomerBalanceHistory.getCustomer().changeBalance(
                preCustomerBalanceHistory.getCurrentBalance() + request.getMoney()
        ); // Customer Balance 변경

        return this.customerBalanceHistoryRepository.save(
                CustomerBalanceHistory.builder()
                        .customer(preCustomerBalanceHistory.getCustomer())
                        .changedBalance(request.getMoney())
                        .currentBalance(preCustomerBalanceHistory.getCurrentBalance() + request.getMoney())
                        .fromMessage(request.getFrom())
                        .description(request.getMessage())
                        .build()
        );
    }
}
