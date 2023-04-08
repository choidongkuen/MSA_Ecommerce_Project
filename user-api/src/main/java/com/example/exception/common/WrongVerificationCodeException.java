package com.example.exception.common;

public class WrongVerificationCodeException extends RuntimeException{
    public WrongVerificationCodeException(String msg) {
        super(msg);
    }
}
