package com.example.exception.product;

import com.example.domain.entity.Product;

public class ProductItemAlreadyExistException extends RuntimeException {
    public ProductItemAlreadyExistException(String msg) {
        super(msg);
    }
}
