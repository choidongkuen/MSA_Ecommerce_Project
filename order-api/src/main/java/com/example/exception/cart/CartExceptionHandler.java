package com.example.exception.cart;

import com.example.controller.CartController;
import com.example.exception.ErrorMessage;
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
        return ResponseEntity.ok().body(
                ErrorMessage.of(exception, HttpStatus.BAD_REQUEST));
    }
}
