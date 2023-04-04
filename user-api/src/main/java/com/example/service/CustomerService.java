package com.example.service;

import com.example.domain.dto.SignUpForm;
import com.example.domain.entity.CustomerEntity;
import com.example.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService {

    public final CustomerRepository customerRepository;

    @Transactional
    public CustomerEntity signup(SignUpForm request) {
        return this.customerRepository.save(
                CustomerEntity.toEntity(request)
        );
    }
}
