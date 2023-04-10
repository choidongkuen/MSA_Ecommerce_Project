package com.example.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductItemRequestDto {
    private Long id; // item id

    private String name;

    private Integer price;

    private Integer count;

}