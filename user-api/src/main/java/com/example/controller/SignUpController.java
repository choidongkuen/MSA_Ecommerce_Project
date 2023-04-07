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

    @ApiOperation(value = "회원가입")
    @PostMapping
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form) throws AlreadyBoundException {
        return ResponseEntity.ok(signUpApplication.customerSignUp(form));
    }

    @ApiOperation(value = "회원 인증")
    @PutMapping("/verify/customer")
    public ResponseEntity<String> verifyCustomer(@RequestParam String email,
                                                 @RequestParam String code) {
        this.signUpApplication.customerVerify(email,code);
        return ResponseEntity.ok("인증이 정상적으로 완료되었습니다.");
    }
}
