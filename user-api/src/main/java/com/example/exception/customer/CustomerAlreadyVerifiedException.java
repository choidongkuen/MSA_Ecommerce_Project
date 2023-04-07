package com.example.exception.customer;

public class CustomerAlreadyVerifiedException extends RuntimeException {

    public CustomerAlreadyVerifiedException(String msg) {
        super(msg);
    }
}