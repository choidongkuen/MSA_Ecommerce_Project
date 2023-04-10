package com.example.dto.product;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductItem;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemResponseDto {

    private Long sellerId;

    private Integer price;

    private Integer count;

    private Product product;

    public static ProductItemResponseDto of(Long sellerId, ProductItem item) {
        return ProductItemResponseDto.builder()
                .sellerId(sellerId)
                .price(item.getPrice())
                .count(item.getCount())
                .product(item.getProduct())
                .build();
    }
}
