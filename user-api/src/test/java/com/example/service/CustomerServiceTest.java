package com.example.service;

import com.example.domain.dto.SignUpForm;
import com.example.domain.entity.CustomerEntity;
import com.example.domain.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void successSignupTest(){
        // given

        SignUpForm request = SignUpForm
                .builder()
                .email("danaver12@daum.net")
                .name("최동근")
                .phone("010-9017-6902")
                .birthDate(LocalDate.now())
                .password("password")
                .build();

        CustomerEntity customer = CustomerEntity.builder()
                .email("danaver12@daum.net")
                .name("최동근")
                .phone("010-9017-6902")
                .birth(LocalDate.now())
                .password("password")
                .build();
        // when
        given(customerRepository.save(any()))
                .willReturn(customer);
        // then

        CustomerEntity customerEntity = this.customerService.signup(request);

        assertEquals("danaver12@daum.net",customerEntity.getEmail());
        assertEquals("최동근",customerEntity.getName());
     }
}