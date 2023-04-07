package com.example.exception.seller;

public class SellerNotVerifiedException extends RuntimeException {
    public SellerNotVerifiedException(String s) {
        super(s);
    }
}
