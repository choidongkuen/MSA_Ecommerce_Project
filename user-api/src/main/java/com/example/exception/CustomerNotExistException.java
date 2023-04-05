package com.example.exception;

public class CustomerNotExistException extends RuntimeException {
    public CustomerNotExistException(String msg) {
        super(msg);
    }
}
