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
public class ProductResponseDto {

    private Long id;

    private String name;

    private List<ProductItemResponseDto> productItems = new ArrayList<>();

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .productItems(
                        product.getProductItemList().stream()
                                .map(pl -> ProductItemResponseDto.of(product.getSellerId(),pl))
                                .collect(Collectors.toList())
                ).build();
    }
}
