package com.example.exception;

public class CustomerPasswordNotMatchException extends RuntimeException {
    public CustomerPasswordNotMatchException(String msg) {
        super(msg);
    }
}
