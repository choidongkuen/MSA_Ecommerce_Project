package com.example.domain.entity;

import com.example.dto.cart.AddCartProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("basket")
public class Cart {

    @Id
    private Long customerId;

    private List<Product> products = new ArrayList<>();

    private List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        this.messages.add(message);
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {

        private Long id;

        private Long sellerId;

        private String name;

        private String description;

        private List<ProductItem> productItems = new ArrayList<>();

        public static Product from(AddCartProductRequestDto request) {
            return Product.builder()
                          .id(request.getId())
                          .sellerId(request.getSellerId())
                          .name(request.getName())
                          .description(request.getDescription())
                          .productItems(request.getProductItems().stream()
                                               .map(ProductItem::from)
                                               .collect(Collectors.toList()))
                          .build();

        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductItem {

        private Long id;

        private String name;

        private Integer count;

        private Integer price;

        public static ProductItem from(AddCartProductRequestDto.ProductItem request) {
            return ProductItem.builder()
                              .id(request.getId())
                              .name(request.getName())
                              .count(request.getCount())
                              .build();
        }

        public void setCount(Integer changedCount) {
            this.count = changedCount;
        }

    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
