package com.example.domain.entity;

import com.example.dto.product.AddProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JoinColumn(name = "productItems")
    private List<ProductItem> productItemList = new ArrayList<>();

    public static Product of(Long sellerId, AddProductRequestDto dto) {
        return Product.builder()
                  .sellerId(sellerId)
                  .name(dto.getName())
                  .description(dto.getDescription())
                  .productItemList(dto.getAddProductItemRequestDtos()
                      .stream()
                      .map((e) -> ProductItem.of(sellerId, e))
                      .collect(Collectors.toList()))
                    .build();
    }
}


