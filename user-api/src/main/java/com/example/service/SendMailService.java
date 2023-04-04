package com.example.service;

import com.example.client.MailgunClient;
import com.example.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SendMailService {

    private final MailgunClient client;

    public String sendMail() {

        SendMailForm form = SendMailForm.builder()
                                        .from("zerobase@naver.com")
                                        .to("danaver12@daum.net")
                                        .subject("this is the test")
                                        .text("테스트입니다.")
                                        .build();

        return client.sendEmail(form).getBody();
    }
}
