package com.example.dto.product;

import com.example.domain.entity.ProductItem;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductItemResponseDto {


    private Long id;

    private String name;

    private Integer price;

    private Integer count;

    public static AddProductItemResponseDto from(ProductItem entity) {
        return AddProductItemResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .count(entity.getCount())
                .build();
    }
}
