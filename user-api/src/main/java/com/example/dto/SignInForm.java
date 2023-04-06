package com.example.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {

    private String email;

    private String password;
}
