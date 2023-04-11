package com.example.service;

import com.example.domain.entity.Cart;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductItem;
import com.example.domain.repository.ProductItemRepository;
import com.example.domain.repository.ProductRepository;
import com.example.dto.cart.AddCartProductRequestDto;
import com.example.dto.product.*;
import com.example.exception.product.ProductAlreadyExistException;
import com.example.exception.product.ProductItemAlreadyExistException;
import com.example.exception.product.ProductItemNotExistException;
import com.example.exception.product.ProductNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    /**
     * 1. 상품 등록
     * 2. 상품 아이템 등록
     * 3. 상품 수정
     * 4. 상품 아이템 수정
     */

    /*
       1. productItem 저장이 안됨
     */
    private final ProductRepository productRepository;

    private final ProductItemRepository productItemRepository;


    @Transactional
    public Product addProduct(Long sellerId, AddProductRequestDto request) {

        if (this.productRepository.existsBySellerIdAndName(sellerId, request.getName())) {
            throw new ProductAlreadyExistException("일치하는 상품이 이미 존재합니다.");
        }

        Product product = this.productRepository.save(Product.of(sellerId, request));

        for (ProductItem pi : product.getProductItemList()) {
            pi.setProduct(product);
            this.productItemRepository.save(pi);
        }
        return product;
    }

    /*
        1. product 가존재해야 함
        2. product productItemList 의 해당 productItem이 존재하면 안됨
        3. 반환 타입으로 Product
     */
    @Transactional
    public Product addProductItem(Long sellerId, AddProductItemRequestDto request) {

        Product product = this.productRepository.findBySellerIdAndId(sellerId, request.getProductId())
                                                .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        if (product.getProductItemList().stream().anyMatch(
                productItem -> productItem.getName().equals(request.getName()))
        ) {
            throw new ProductItemAlreadyExistException("일치하는 상품 아이템이 이미 존재합니다.");
        }

        ProductItem item = ProductItem.of(sellerId, request);
        item.setProduct(product);
        product.getProductItemList().add(item); // 영속성 전이를 이용하기에 productItem save 안해줘도 됨

        return product;
    }

    /*
        1. product 가존재해야함
        2. 변경 대상 name , description
     */

    @Transactional
    public Product updateProduct(Long sellerId, UpdateProductRequestDto request) {

        Product product = this.productRepository.findBySellerIdAndId(sellerId, request.getId())
                                                .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        for (UpdateProductItemRequestDto updateProductItemRequestDto : request.getItems()) {
            ProductItem item = product.getProductItemList()
                                      .stream()
                                      .filter(pi -> pi.getId().equals(updateProductItemRequestDto.getId()))
                                      .findFirst().orElseThrow(() -> new ProductItemNotExistException("일치하는 상품 아이템이 존재하지 않습니다."));
            item.updateProductItem(updateProductItemRequestDto);
        }

        return product.updateProduct(request);
    }


    /*
        1. productItem 이 존재해야함
        2. name,price 그리고 count 변경

     */
    @Transactional
    public Product updateProductItem(Long sellerId, UpdateProductItemRequestDto request) {

        ProductItem productItem = this.productItemRepository.findById(request.getId())
                                                            .filter(pi -> Objects.equals(pi.getSellerId(), sellerId))
                                                            .orElseThrow(() -> new ProductItemNotExistException("일치하는 상품 아이템이 존재하지 않습니다."));


        return productItem.updateProductItem(request);
    }

    @Transactional
    public void deleteProduct(Long sellerId, Long productId) {

        Product product = this.productRepository.findBySellerIdAndId(sellerId, productId)
                                                .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        this.productRepository.delete(product); // Cascade.ALL -> ProductItem 도 같이 삭제
    }

    @Transactional
    public void deleteProductItem(Long sellerId, Long productItemId) {

        ProductItem productItem = this.productItemRepository.findById(productItemId)
                                                            .filter(pi -> pi.getSellerId().equals(sellerId))
                                                            .orElseThrow(() -> new ProductItemNotExistException("일치하는 상품 아이템이 존재하지 않습니다."));
        this.productItemRepository.delete(productItem);
    }

    /*
      1. name 에 맞는 product 조회
     */
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAllByName(String name) {
        return this.productRepository.findAllByName(name)
                                     .stream()
                                     .map(ProductResponseDto::from)
                                     .collect(Collectors.toList());
    }

    /*
      1. 상세조회
     */
    @Transactional(readOnly = true)
    public ProductResponseDto searchDetailProduct(Long productId) {
        return this.productRepository.findById(productId)
                                     .map(ProductResponseDto::from)
                                     .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));


    }

    @Transactional
    public Optional<Product> getProductById(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Transactional
    public void minusProductItem(List<Cart.ProductItem> items) {
        for(Cart.ProductItem item : items){

        }
    }
}

