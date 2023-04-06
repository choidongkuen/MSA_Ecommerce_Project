package com.example.config;


import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// user-api 에서 domain 모듈을 가져왔어도 bean 따로 등록 필요! (자동 등록 x)
@Configuration
public class JwtConfig {
    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider();
    }
}
