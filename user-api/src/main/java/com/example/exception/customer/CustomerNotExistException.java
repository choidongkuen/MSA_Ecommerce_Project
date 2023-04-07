package com.example.exception.customer;

public class CustomerNotExistException extends RuntimeException {
    public CustomerNotExistException(String msg) {
        super(msg);
    }
}
