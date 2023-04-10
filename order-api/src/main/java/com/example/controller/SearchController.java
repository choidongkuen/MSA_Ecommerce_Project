package com.example.controller;

import com.example.dto.product.ProductResponseDto;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.config.JwtAuthenticationProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/search/product")
@RestController
public class SearchController {

    private final ProductService productService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    // domain 모듈에서온 JwtAuthenticationProvider 사용해야함

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> searchProduct(
            @RequestParam String name
    ) {
        return ResponseEntity.ok().body(
                this.productService.findAllByName(name));
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<ProductResponseDto> searchDetailProduct(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok().body(
                this.productService.searchDetailProduct(productId)
        );
    }
}
