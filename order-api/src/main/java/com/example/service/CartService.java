package com.example.service;

import com.example.client.RedisClient;
import com.example.domain.entity.Cart;
import com.example.dto.cart.AddCartProductRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartService {

    private final RedisClient redisClient; // RedisClient 오버라이딩

    public Cart getCart(Long customerId) {
        return this.redisClient.get(customerId, Cart.class);
    }

    public Cart addCart(Long customerId, AddCartProductRequestDto request) {

        Cart cart = redisClient.get(customerId, Cart.class);

        if (cart == null) {
            cart = new Cart();
            cart.setCustomerId(customerId);
        } // 해당 고객(Customer) 의 장바구니(Cart) 가 없는 경우

        // 이전에 같은 상품이 있는지 체크 필수
        Optional<Cart.Product> optionalProduct = cart.getProducts().stream()
                      .filter(p -> Objects.equals(p.getId(), request.getId()))
                      .findFirst();

        // 이전에 같은 상품이 있다면
        if(optionalProduct.isPresent()) {
            // redis 이미 있는 product
            Cart.Product redisProduct = optionalProduct.get();

            // requested
            List<Cart.ProductItem> items
                    = request.getProductItems().stream()
                    .map(Cart.ProductItem :: from)
                    .collect(Collectors.toList());

            // 검색 향상을 위해 map
            Map<Long,Cart.ProductItem> redisItemMap = redisProduct.getProductItems()
                    .stream()
                    .collect(Collectors.toMap(Cart.ProductItem::getId, item -> item));

            if(!redisProduct.getName().equals(request.getName())) { // 기존 Cart.Product 이름 != 추가하려는 Product 이름
                cart.addMessage(redisProduct + "의 정보가 변경되었습니다. 확인 부탁드립니다.");
            }


            // ProductItem 이 달라지는 경우 체크
            for(Cart.ProductItem item : items) {
                Cart.ProductItem redisItem = redisItemMap.get(item.getId());

                if(redisItem == null) {
                    redisProduct.getProductItems().add(item); // happy case
                } else {
                    if(!redisItem.getPrice().equals(item.getPrice())) {
                        cart.addMessage(redisProduct.getName()+" "+ item.getName() + " 의 가격으로 변경되었습니다.");
                    }
                    redisItem.setCount(redisItem.getCount()+item.getCount()); // 개수 추가
                }
            }
        } else {
            Cart.Product product = Cart.Product.from(request);
            cart.getProducts().add(product);
            redisClient.put(customerId,cart);
            return cart;
        }
        redisClient.put(customerId,cart);
        return cart;
    }
}
