package com.example.application;

import com.example.client.MailgunClient;
import com.example.domain.entity.Customer;
import com.example.domain.entity.Seller;
import com.example.dto.SignUpForm;
import com.example.exception.customer.CustomerAlreadyExistException;
import com.example.exception.seller.SellerAlreadyExistException;
import com.example.mailgun.SendMailForm;
import com.example.service.customer.CustomerSignUpService;
import com.example.service.seller.SellerSignUpService;
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

    private final CustomerSignUpService customerSignUpService;

    private final SellerSignUpService sellerSignUpService;

    public void verifyCustomer(String email, String code) {
        this.customerSignUpService.verifyEmail(email, code);
    }

    public void verifySeller(String email, String code) {
        this.sellerSignUpService.verifyEmail(email, code);
    }

    public String customerSignUp(SignUpForm request) throws AlreadyBoundException { // Customer
        if (customerSignUpService.isEmailExist(request.getEmail())) {
            throw new CustomerAlreadyExistException("이미 존재하는 회원입니다.");
        }
        Customer storedCustomer = this.customerSignUpService.signup(request); // 저장
        String code = getRandomCode();

        mailGunClient.sendEmail(SendMailForm.builder()
                                            .from("test@ehdrms.com")
                                            .to(request.getEmail())
                                            .subject("Verification Email")
                                            .text(getVerificationEmailBody(request.getEmail(), request.getName(), "customer", code))
                                            .build()
        ); // 메일 전송
        customerSignUpService.changeCustomerValidateEmail(storedCustomer.getId(), code); // 회원 인증 관련 데이터 저장
        return "회원가입 정상 처리되었습니다.";
    }

    public String sellerSignUp(SignUpForm request) throws AlreadyBoundException { // Seller
        if (sellerSignUpService.isEmailExist(request.getEmail())) {
            throw new SellerAlreadyExistException("이미 존재하는 판매자입니다.");
        }

        Seller storedSeller = this.sellerSignUpService.signup(request); // 저장
        String code = getRandomCode();

        mailGunClient.sendEmail(SendMailForm.builder()
                                            .from("test@ehdrms.com")
                                            .to(request.getEmail())
                                            .subject("Verification Email")
                                            .text(getVerificationEmailBody(request.getEmail(), request.getName(), "seller", code))
                                            .build()
        ); // 메일 전송
        sellerSignUpService.changeSellerValidateEmail(storedSeller.getId(), code); // 판매자 인증 관련 데이터 저장
        return "회원가입 정상 처리되었습니다.";
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true); // 10 자리
    }

    private String getVerificationEmailBody(String email, String name, String type, String code) {
        StringBuilder sb = new StringBuilder();
        return sb.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
                 .append("http://localhost:8080/signup/" + type + "/verify/?email=")
                 .append(email)
                 .append("&code=")
                 .append(code).toString();
    }
}
