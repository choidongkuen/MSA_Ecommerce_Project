package com.example.mailgun;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMailForm {

    private String from;
    private String to;
    private String subject;
    private String text;
}
