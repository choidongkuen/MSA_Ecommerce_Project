package com.example.exception.common;

public class ExceedVerificationExpiredAtException extends RuntimeException{
    public ExceedVerificationExpiredAtException(String msg) {
        super(msg);
    }
}
