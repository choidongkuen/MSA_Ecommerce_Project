package com.example.exception.product;

import lombok.Getter;

@Getter
public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
