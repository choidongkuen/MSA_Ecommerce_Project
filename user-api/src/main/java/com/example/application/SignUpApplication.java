package com.example.application;

import com.example.client.MailgunClient;
import com.example.domain.dto.SignUpForm;
import com.example.domain.entity.Customer;
import com.example.mailgun.SendMailForm;
import com.example.service.CustomerSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpApplication {

    private final MailgunClient mailGunClient;

    private final CustomerSignUpService signUpService;

    public void customerVerify(String email, String code) {
        this.signUpService.verifyEmail(email,code);
    }

    public String customerSignUp(SignUpForm request) throws AlreadyBoundException {
        if (signUpService.isEmailExist(request.getEmail())) {
            throw new AlreadyBoundException("이미 존재하는 회원입니다.");
        }
        Customer storedCustomer = this.signUpService.signup(request); // 저장
        String code = getRandomCode();

        mailGunClient.sendEmail(SendMailForm.builder()
                                            .from("test@ehdrms.com")
                                            .to(request.getEmail())
                                            .subject("Verification Email")
                                            .text(getVerificationEmailBody(request.getEmail(), request.getName(), code))
                                            .build()
        ); // 메일 전송
        signUpService.changeCustomerValidateEmail(storedCustomer.getId(), code);
        return "회원가입 정상 처리되었습니다.";
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true); // 10 자리
    }

    private String getVerificationEmailBody(String email, String name, String code) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
                 .append("http://localhost:8080/signup/verify/customer?email=")
                 .append(email)
                 .append("&code=")
                 .append(code).toString();
    }
}
