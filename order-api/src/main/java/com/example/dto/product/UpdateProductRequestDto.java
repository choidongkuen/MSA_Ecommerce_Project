package com.example.dto.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDto {

    private Long id;

    private String name;

    private String description;

    private List<UpdateProductItemRequestDto> items;
}