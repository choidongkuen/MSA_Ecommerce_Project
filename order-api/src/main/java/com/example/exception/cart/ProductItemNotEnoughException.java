package com.example.exception.cart;

public class ProductItemNotEnoughException extends RuntimeException {
    public ProductItemNotEnoughException(String msg) {
        super(msg);
    }
}
