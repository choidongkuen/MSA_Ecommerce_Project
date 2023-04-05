package com.example.exception;

public class ExceedVerificationExpiredAtException extends RuntimeException{
    public ExceedVerificationExpiredAtException(String msg) {
        super(msg);
    }
}
