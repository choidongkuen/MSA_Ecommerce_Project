package com.example.controller;

import com.example.application.SignUpApplication;
import com.example.dto.SignUpForm;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.AlreadyBoundException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/signup")
@RestController
public class SignUpController {


    private final SignUpApplication signUpApplication;


    @ApiOperation(value = "고객 회원가입")
    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) throws AlreadyBoundException {
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @ApiOperation(value = "고객 회원 인증")
    @GetMapping("/customer/verify") // 링크 클릭
    public ResponseEntity<String> verifyCustomer(@RequestParam String email,
                                                 @RequestParam String code) {
        this.signUpApplication.verifyCustomer(email, code);
        return ResponseEntity.ok("인증이 정상적으로 완료되었습니다.");
    }

    @ApiOperation(value = "판매자 회원가입")
    @PostMapping("/seller")
    public ResponseEntity<String> sellerSignUp(@RequestBody SignUpForm form) throws AlreadyBoundException {
        return ResponseEntity.ok(signUpApplication.sellerSignUp(form));
    }

    @ApiOperation(value = "판매자 인증")
    @GetMapping("/seller/verify") // 링크 클릭
    public ResponseEntity<String> verifySeller(@RequestParam String email,
                                               @RequestParam String code) {
        this.signUpApplication.verifySeller(email, code);
        return ResponseEntity.ok("인증이 정상적으로 완료되었습니다.");
    }

}
