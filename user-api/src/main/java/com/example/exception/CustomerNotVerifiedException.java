package com.example.exception;

public class CustomerNotVerifiedException extends RuntimeException {
    public CustomerNotVerifiedException(String msg) {
        super(msg);
    }
}
