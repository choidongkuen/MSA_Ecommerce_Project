package com.example.service;

import com.example.domain.entity.Product;
import com.example.domain.repository.ProductRepository;
import com.example.dto.product.AddProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    /**
     * 상품 등록
     */
    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(Long sellerId, AddProductRequestDto request) {
        return this.productRepository.save(Product.of(sellerId, request));
    }
}
