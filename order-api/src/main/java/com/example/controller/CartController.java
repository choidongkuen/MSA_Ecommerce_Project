package com.example.controller;

import com.example.application.CartApplication;
import com.example.domain.config.JwtAuthenticationProvider;
import com.example.domain.entity.Cart;
import com.example.dto.cart.AddCartProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/customer/cart")
@RestController
public class CartController {

    private final CartApplication cartApplication;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;


    @PostMapping
    public ResponseEntity<Cart> addCart(
            @RequestHeader("Authorization") String token,
            @RequestBody AddCartProductRequestDto request
    ) {
        Long customerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                this.cartApplication.addCart(customerId, request)
        );
    }
}
