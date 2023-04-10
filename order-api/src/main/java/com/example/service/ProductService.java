package com.example.service;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductItem;
import com.example.domain.repository.ProductItemRepository;
import com.example.domain.repository.ProductRepository;
import com.example.dto.product.AddProductItemRequestDto;
import com.example.dto.product.AddProductRequestDto;
import com.example.exception.product.ProductItemAlreadyExistException;
import com.example.exception.product.ProductNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    /**
     * 1. 상품 등록
     * 2. 상품 아이템 등록
     */

    /*
       1. productItem 저장이 안됨
     */
    private final ProductRepository productRepository;

    private final ProductItemRepository productItemRepository;

    @Transactional
    public Product addProduct(Long sellerId, AddProductRequestDto request) {
        return this.productRepository.save(Product.of(sellerId, request));
    }

    /*
        1. product 가 존재해야 함
        2. product productItemList 의 해당 productItem이 존재하면 안됨
        3. 반환 타입으로 Product
     */
    @Transactional
    public Product addProductItem(Long sellerId, AddProductItemRequestDto request) {

        Product product = this.productRepository.findBySellerIdAndId(sellerId,request.getProductId())
                .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        if(product.getProductItemList().stream().anyMatch(
                productItem -> productItem.getName().equals(request.getName()))
        ) {
            throw new ProductItemAlreadyExistException("일치하는 상품 아이템이 이미 존재합니다.");
        }

        ProductItem item = ProductItem.of(sellerId, request);
        product.getProductItemList().add(item);
        return item.setProduct(product);
    }
}
