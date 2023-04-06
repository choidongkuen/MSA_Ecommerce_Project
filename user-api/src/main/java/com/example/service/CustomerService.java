package com.example.service;

import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.exception.CustomerNotVerifiedException;
import com.example.exception.CustomerPasswordNotMatchException;
import com.example.exception.CustomerNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findByValidCustomer(String email, String password) {
        Customer customer = this.customerRepository.findByEmail(email) // 이메일 존재하지 않는 경우
                               .orElseThrow(() -> new CustomerNotExistException("일치하는 회원이 존재하지 않습니다."));

        if (!customer.isVerified()) { // 인증이 이루어지지 않는 회원인 경우
            throw new CustomerNotVerifiedException("해당 회원은 인증이 이루어지지 않았습니다.");
        }
        if (!customer.getPassword().equals(password)) { // 입력한 비밀번호와 저장된 비밀번호가 일치하지 않는 경우
            throw new CustomerPasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        return customer;
    }

    public Optional<Customer> findByIdAndEmail(Long id, String email) {
        return this.customerRepository.findByIdAndEmail(id,email);
    }
}
