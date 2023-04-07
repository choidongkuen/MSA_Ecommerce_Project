package com.example.controller;


import com.example.application.SignInApplication;
import com.example.dto.SignInForm;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/signin")
@RestController
public class SignInController {


    private final SignInApplication signInApplication;

    @ApiOperation("고객 로그인")
    @PostMapping("/customer")
    public ResponseEntity<String> signInCustomer(
            @RequestBody SignInForm request
    ) {
        return ResponseEntity.ok().body(this.signInApplication.customerLoginToken(request));
    }

    @ApiOperation("판매자 로그인")
    @PostMapping("/seller")
    public ResponseEntity<String> signInSeller(
            @RequestBody SignInForm request
    ) {
        return ResponseEntity.ok().body(this.signInApplication.sellerLogInToken(request));
    }
}
