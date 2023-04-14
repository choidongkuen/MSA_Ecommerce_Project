package com.example.client;

import com.example.domain.entity.Cart;
import com.example.exception.cart.CartAddImpossibleException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisClient {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> T get(Long key, Class<T> classType) { // Long 타입의 key(String으로 일괄 처리)
        return this.get(key.toString(), classType);
    }

    public <T> T get(String key, Class<T> classType) { // String 타입의 key
        String redisValue = (String) redisTemplate.opsForValue().get(key); // valueOperations
        if (ObjectUtils.isEmpty(redisValue)) {
            return null;
        } else {
            try {
                return mapper.readValue(redisValue, classType);
            } catch (JsonProcessingException exception) {
                log.error("Parsing Error", exception);
                return null;
            }
        }
    }

    public void put(Long key, Cart cart) { // Long 타입의 key
        put(key.toString(),cart);
    }

    public void put(String key, Cart cart) { // String 타입의 key
        try{
            redisTemplate.opsForValue().set(key,mapper.writeValueAsString(cart));
        } catch (JsonProcessingException e) {
            throw new CartAddImpossibleException("장바구니에 추가할 수 없습니다.");
        }
    }
}
