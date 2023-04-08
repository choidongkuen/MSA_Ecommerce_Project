package com.example.dto.product;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddProductItemRequestDto {

    private Long productId;

    private String name;

    private Integer price;

    private Integer count;
}
