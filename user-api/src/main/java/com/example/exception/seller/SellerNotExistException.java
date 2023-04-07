package com.example.exception.seller;

public class SellerNotExistException extends RuntimeException {
    public SellerNotExistException(String message) {
        super(message);
    }
}
