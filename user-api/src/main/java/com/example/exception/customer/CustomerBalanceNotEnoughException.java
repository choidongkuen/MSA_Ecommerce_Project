package com.example.exception.customer;

public class CustomerBalanceNotEnoughException extends RuntimeException {
    public CustomerBalanceNotEnoughException(String s) {
        super(s);
    }
}
