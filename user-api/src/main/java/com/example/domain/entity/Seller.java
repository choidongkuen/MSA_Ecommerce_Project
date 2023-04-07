package com.example.domain.entity;

import com.example.dto.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Seller extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "BIRTH")
    private LocalDate birth;

    private LocalDateTime verifyExpiredAt;

    private String verificationCode;

    private boolean verified;

    public void setVerificationCode(String code) {
        this.verificationCode = code;
    }

    public void setVerifyExpiredAt(LocalDateTime time) {
        this.verifyExpiredAt = time;
    }

    public void setVerified() {
        this.verified = true;
    }

    public static Seller toEntity(SignUpForm request) {
        return Seller.builder()
                .email(request.getEmail().toLowerCase(Locale.ROOT))
                .password(request.getPassword())
                .name(request.getName())
                .password(request.getPassword())
                .phone(request.getPhone())
                .birth(request.getBirthDate())
                .verified(false)
                .build();
    }
}