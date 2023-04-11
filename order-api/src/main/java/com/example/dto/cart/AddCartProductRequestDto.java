package com.example.dto.cart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCartProductRequestDto {

    private Long id;

    private Long sellerId;

    private String name;

    private String description;

    private List<ProductItem> productItems;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductItem {
        private Long id;
        private String name;

        private Integer price;
        private Integer count;

        private String description;
    }
}
