package com.example.dto.product;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequestDto {

    private String name;

    private String description;

    private List<AddProductItemRequestDto> addProductItemRequestDtos;

}
