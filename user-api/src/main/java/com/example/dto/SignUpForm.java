package com.example.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm { // 회원가입을 위한 DTO

    private String email;
    private String name;
    private String password;
    private LocalDate birthDate;
    private String phone;
}
