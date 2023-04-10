package com.example.exception.product;

public class ProductItemNotExistException extends RuntimeException {
    public ProductItemNotExistException(String msg) {
        super(msg);
    }
}
