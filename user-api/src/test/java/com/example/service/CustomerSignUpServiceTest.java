package com.example.service;

import com.example.domain.dto.SignUpForm;
import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomerSignUpServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerSignUpService customerSignUpService;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void successSignupTest() {
        // given

        SignUpForm request = SignUpForm
                .builder()
                .email("danaver12@daum.net")
                .name("최동근")
                .phone("010-9017-6902")
                .birthDate(LocalDate.now())
                .password("password")
                .build();

        Customer customer = Customer.builder()
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

        Customer customerEntity = this.customerSignUpService.signup(request);

        assertEquals("danaver12@daum.net", customerEntity.getEmail());
        assertEquals("최동근", customerEntity.getName());
    }
}