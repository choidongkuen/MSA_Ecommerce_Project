package com.example.dto.product;

import com.example.domain.entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductResponseDto {

    private Long id;

    private String name;

    private List<AddProductItemResponseDto> productItems = new ArrayList<>();

    public static AddProductResponseDto from(Product product) {
        return AddProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .productItems(
                        product.getProductItemList().stream()
                                .map(AddProductItemResponseDto::from)
                                .collect(Collectors.toList())
                ).build();
    }
}
