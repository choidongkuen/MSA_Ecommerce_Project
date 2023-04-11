package com.example.exception.cart;

import com.example.controller.CartController;
import com.example.exception.ErrorMessage;
import com.example.exception.product.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {CartController.class})
public class CartExceptionHandler {

    @ExceptionHandler(CartAddImpossibleException.class)
    public ResponseEntity<ErrorMessage> cartAddImpossibleExceptionHandler(
            Exception exception
    ) {
        return ResponseEntity.badRequest().body(
                ErrorMessage.of(exception, HttpStatus.BAD_REQUEST)
        );
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorMessage> productNotExistExceptionHandler(
            Exception exception
    ) {
        return ResponseEntity.badRequest().body(
                ErrorMessage.of(exception, HttpStatus.BAD_REQUEST)
        );
    }

    @ExceptionHandler(ProductItemNotEnoughException.class)
    public ResponseEntity<ErrorMessage> productItemNotEnoughExceptionHandler(
            Exception exception
    ) {
        return ResponseEntity.badRequest().body(
                ErrorMessage.of(exception, HttpStatus.BAD_REQUEST)
        );
    }
}
