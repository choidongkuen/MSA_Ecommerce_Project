package com.example.service;

import com.example.config.FeignConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class SendMailServiceTest {

    @Autowired
    private SendMailService sendMailService;

    @Test
    @DisplayName("이메일 보내기 테스트")
    void sendEmailTest() {

        String response = sendMailService.sendMail();
        System.out.println(response);
    }
}
