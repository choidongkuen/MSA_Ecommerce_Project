package com.example.exception.product;

import com.example.controller.SearchController;
import com.example.controller.SellerProductController;
import com.example.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses =
        {SellerProductController.class, SearchController.class}
)
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorMessage> productNotExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductItemAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> productItemAlreadyExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> productAlreadyExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ProductItemNotExistException.class)
    public ResponseEntity<ErrorMessage> productItemNotExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }
}
