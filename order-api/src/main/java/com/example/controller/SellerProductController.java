package com.example.controller;

import com.example.dto.product.AddProductRequestDto;
import com.example.dto.product.AddProductResponseDto;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/seller/product")
@RestController
public class SellerProductController {

    private final ProductService productService;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;


    @PostMapping()
    public ResponseEntity<AddProductResponseDto> addProduct(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody AddProductRequestDto request
    ) {

        Long sellerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                AddProductResponseDto.from(this.productService.addProduct(sellerId, request))
        );

    }
}
