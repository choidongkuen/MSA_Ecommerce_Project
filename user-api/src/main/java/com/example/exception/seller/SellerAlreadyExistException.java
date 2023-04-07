package com.example.exception.seller;

public class SellerAlreadyExistException extends RuntimeException{
    public SellerAlreadyExistException(String s) {
        super(s);
    }
}
