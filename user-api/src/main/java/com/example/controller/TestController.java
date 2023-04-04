package com.example.controller;

import com.example.service.SendMailService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SendMailService sendMailService;

    @GetMapping
    public String sendEmailTest() {

        return sendMailService.sendMail();
    }
}
