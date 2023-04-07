package com.example.application;


import com.example.domain.entity.Customer;
import com.example.domain.entity.Seller;
import com.example.dto.SignInForm;
import com.example.service.customer.CustomerService;
import com.example.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.common.UserType;
import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignInApplication {

    private final CustomerService customerService;

    private final SellerService sellerService;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public String customerLoginToken(SignInForm request) {
        // 1. 로그인 가능 여부
        Customer customer
                = this.customerService.findByValidCustomer(request.getEmail(), request.getPassword());
        // 2. 토큰을 발행
        String token =
                jwtAuthenticationProvider.createToken(customer.getEmail(),customer.getId(), UserType.CUSTOMER);

        // 3. 응답에 담아 응답
        return token;
    }

    public String sellerLogInToken(SignInForm request) {

        Seller seller
                = this.sellerService.findByValidCustomer(request.getEmail(), request.getPassword());

        String token =
                jwtAuthenticationProvider.createToken(seller.getEmail(), seller.getId(), UserType.SELLER);

        return token;
    }
}
