package com.example.domain.entity;

import com.example.domain.dto.SignUpForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    private LocalDate birth;

    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verified;
    public static Customer toEntity(SignUpForm form) {
        return Customer.builder()
                       .email(form.getEmail())
                       .name(form.getName())
                       .password(form.getPassword())
                       .phone(form.getPhone())
                       .birth(form.getBirthDate())
                       .build();
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setVerifyExpiredAt(LocalDateTime time) {
        this.verifyExpiredAt = time;
    }

    public void setVerified() {
        this.verified = true;
    }

    public boolean isVerified() {
        return this.verified;
    }
}
