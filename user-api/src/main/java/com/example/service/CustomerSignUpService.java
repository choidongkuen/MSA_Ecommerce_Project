package com.example.service;

import com.example.domain.dto.SignUpForm;
import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.exception.CustomerAlreadyVerifiedException;
import com.example.exception.CustomerNotExistException;
import com.example.exception.ExceedVerificationExpiredAtException;
import com.example.exception.WrongVerificationCodeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerSignUpService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer signup(SignUpForm request) {
        return this.customerRepository.save(
                Customer.toEntity(request)
        );
    }

    public boolean isEmailExist(String email) { // 회원가입 이메일에 대해 이미 존재하는 이메일인지 체크
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    @Transactional
    public LocalDateTime changeCustomerValidateEmail(Long customerId,String verificationCode) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotExistException("일치하는 회원이 존재하지 않습니다.");
        }

        Customer customer = optionalCustomer.get();
        customer.setVerificationCode(verificationCode);
        customer.setVerifyExpiredAt(LocalDateTime.now().plusDays(1));
        return customer.getVerifyExpiredAt();
    }

    @Transactional
    public void verifyEmail(String email, String code) { // 이메일 인증 로직
        Customer customer = this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotExistException("일치하는 회원이 존재하지 않습니다."));

        if(customer.isVerified()) {
            throw new CustomerAlreadyVerifiedException("해당 회원은 이미 인증이 완료되었습니다.");
        }

        if(!customer.getVerificationCode().equals(code)) {
            throw new WrongVerificationCodeException("잘못된 인증 시도입니다.");
        }

        if(customer.getVerifyExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ExceedVerificationExpiredAtException("인증 시간이 만료되었습니다.");
        }
        customer.setVerified();
    }
}
