package com.example.exception;

public class CustomerAlreadyVerifiedException extends RuntimeException {

    public CustomerAlreadyVerifiedException(String msg) {
        super(msg);
    }
}