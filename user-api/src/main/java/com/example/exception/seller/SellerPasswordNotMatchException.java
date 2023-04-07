package com.example.exception.seller;

public class SellerPasswordNotMatchException extends RuntimeException {
    public SellerPasswordNotMatchException(String s) {
        super(s);
    }
}
