package com.example.exception;

public class WrongVerificationCodeException extends RuntimeException{
    public WrongVerificationCodeException(String msg) {
        super(msg);
    }
}
