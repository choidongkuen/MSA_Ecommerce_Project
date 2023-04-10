package com.example.controller;

import com.example.dto.product.AddProductItemRequestDto;
import com.example.dto.product.AddProductRequestDto;
import com.example.dto.product.ProductResponseDto;
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


    @PostMapping // Product 등록
    public ResponseEntity<ProductResponseDto> addProduct(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody AddProductRequestDto request
    ) {
        Long sellerId = getSellerId(token);
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.addProduct(sellerId, request))
        );

    }

    @PostMapping // productItem 등록
    public ResponseEntity<ProductResponseDto> addProductItem(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody AddProductItemRequestDto request
    ) {
        Long sellerId = getSellerId(token);
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.addProductItem(sellerId, request))
        );
    }

    private Long getSellerId(String token) {
        return this.jwtAuthenticationProvider.getUserVo(token).getUserId();
    }
}
