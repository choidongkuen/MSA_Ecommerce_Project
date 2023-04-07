package com.example.exception.customer;

public class CustomerPasswordNotMatchException extends RuntimeException {
    public CustomerPasswordNotMatchException(String msg) {
        super(msg);
    }
}
