package com.example.exception.cart;

public class CartAddImpossibleException extends RuntimeException {
    public CartAddImpossibleException(String msg) {
        super(msg);
    }
}
