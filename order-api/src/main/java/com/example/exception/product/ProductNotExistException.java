package com.example.exception.product;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
