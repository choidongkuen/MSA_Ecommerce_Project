package com.example.exception.customer;

public class CustomerNotVerifiedException extends RuntimeException {
    public CustomerNotVerifiedException(String msg) {
        super(msg);
    }
}
