package com.example.domain.entity;


import com.example.dto.product.AddProductItemRequestDto;
import com.example.dto.product.UpdateProductItemRequestDto;
import com.example.dto.product.UpdateProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    private String name;

    private Integer price;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public static ProductItem of(Long sellerId, AddProductItemRequestDto request) {
        return ProductItem.builder()
                .sellerId(sellerId)
                .product(null)
                .name(request.getName())
                .price(request.getPrice())
                .count(request.getCount())
                .build();

    }

    public Product setProduct(Product product) {
        this.product = product;
        return this.product;
    }

    public Product updateProductItem(UpdateProductItemRequestDto request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.count = request.getCount();

        return this.product;
    }

    public void minusItem(Integer count) {
        this.count -= count;
    }
}
