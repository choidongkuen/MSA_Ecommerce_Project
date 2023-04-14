package com.example.application;

import com.example.domain.entity.Cart;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductItem;
import com.example.dto.cart.AddCartProductRequestDto;
import com.example.exception.cart.ProductItemNotEnoughException;
import com.example.exception.product.ProductNotExistException;
import com.example.service.CartService;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

/*
    Cart Service 와 Application 분리 -> 부담 분배
*/

@Slf4j
@RequiredArgsConstructor
@Service
public class CartApplication {

    private final ProductService productService;

    private final CartService cartService;

    @Transactional
    public Cart addCart(Long customerId, AddCartProductRequestDto request) {

        Product product = this.productService.getProductById(request.getId())
                                             .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        Cart cart = this.cartService.getCart(customerId);

        if(cart != null && !this.addAble(cart,product,request)) {
            throw new ProductItemNotEnoughException("상품 아이템이 부족합니다.");
        }

//        this.productService.minusProductItem(request.getProductItems());
        return this.cartService.addCart(customerId,request);
    }

    private boolean addAble(Cart cart, Product product, AddCartProductRequestDto request) { // Cart 담기전 수량 확인
        Cart.Product cartProduct = cart.getProducts().stream()
                .filter(p -> p.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotExistException("일치하는 상품이 존재하지 않습니다."));

        Map<Long,Integer> cartItemCountMap = cartProduct.getProductItems().stream() // Cart
                .collect(Collectors.toMap(Cart.ProductItem::getId,Cart.ProductItem::getCount));

        Map<Long,Integer> currentItemCountMap = product.getProductItemList().stream() // Product
                .collect(Collectors.toMap(ProductItem::getId, ProductItem::getCount));

        // 일치하는게 없으면 true
        // 일치하는게 하나라도 있으면 false
        return request.getProductItems().stream().noneMatch(
                requestItem -> {
                    Integer itemCountInCart = cartItemCountMap.get(requestItem.getId());
                    Integer currentCount = currentItemCountMap.get(requestItem.getId());
                    return requestItem.getCount()+itemCountInCart > currentCount; // 수량을 넘는 경우 true
                }
        );
    }
}
