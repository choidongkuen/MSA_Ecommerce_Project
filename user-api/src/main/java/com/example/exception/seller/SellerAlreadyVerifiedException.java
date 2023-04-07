package com.example.exception.seller;

public class SellerAlreadyVerifiedException extends RuntimeException {
    public SellerAlreadyVerifiedException(String s) {
        super(s);
    }
}
