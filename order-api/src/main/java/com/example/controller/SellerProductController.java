package com.example.controller;

import com.example.dto.product.*;
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
        Long sellerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.addProduct(sellerId, request))
        );

    }

    @PostMapping("/item")// productItem 등록
    public ResponseEntity<ProductResponseDto> addProductItem(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody AddProductItemRequestDto request
    ) {
        Long sellerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.addProductItem(sellerId, request))
        );
    }

    @PutMapping() // Product 수정
    public ResponseEntity<ProductResponseDto> updateProduct(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody UpdateProductRequestDto request
    ) {
        Long sellerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.updateProduct(sellerId, request))
        );
    }

    @PutMapping("/item") // ProductItem 수정
    public ResponseEntity<ProductResponseDto> updateProductItem(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody UpdateProductItemRequestDto request
    ) {
        Long sellerId = this.jwtAuthenticationProvider.getUserVo(token).getUserId();
        return ResponseEntity.ok().body(
                ProductResponseDto.from(this.productService.updateProductItem(sellerId, request))
        );
    }
}
