package com.example.exception.product;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String msg) {
        super(msg);
    }
}
