package com.example.service;

import com.example.domain.entity.Product;
import com.example.domain.repository.ProductRepository;
import com.example.dto.product.AddProductItemRequestDto;
import com.example.dto.product.AddProductRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("product 저장 테스트")
    void addProductTest() {
        // given

        Product product = Product.builder()
                                 .id(1L)
                                 .sellerId(1L)
                                 .name("상품")
                                 .description("설명")
                                 .productItemList(new ArrayList<>())
                                 .build();

        List<AddProductItemRequestDto> productItems = new ArrayList<>();

        productItems.add(AddProductItemRequestDto.builder()
                                                 .name("아이템 이름1")
                                                 .productId(1L)
                                                 .count(0)
                                                 .price(10000)
                                                 .build()
        );

        productItems.add(AddProductItemRequestDto.builder()
                                                 .name("아이템 이름2")
                                                 .productId(2L)
                                                 .count(1)
                                                 .price(102000)
                                                 .build()
        );

        AddProductRequestDto dto
                = AddProductRequestDto.builder()
                                      .name("이름")
                                      .description("설명")
                                      .addProductItemRequestDtos(productItems)
                                      .build();

        given(productRepository.save(any())).
                willReturn(product); //when
        // then

        Product result = this.productService.addProduct(1L, dto);

        assertEquals(1L, result.getId());
        assertEquals("설명", result.getDescription());
        assertEquals("상품", result.getName());
        assertEquals(0, result.getProductItemList().size());
    }
}