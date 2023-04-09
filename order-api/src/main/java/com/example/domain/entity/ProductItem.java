package com.example.domain.entity;


import com.example.dto.product.AddProductItemRequestDto;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public static ProductItem of(Long sellerId, AddProductItemRequestDto request) {
        return ProductItem.builder()
                .sellerId(sellerId)
                .name(request.getName())
                .price(request.getPrice())
                .count(request.getCount())
                .build();
    }
}
