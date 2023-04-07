package com.example.exception.seller;

import com.example.controller.SignInController;
import com.example.controller.SignUpController;
import com.example.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {SignInController.class, SignUpController.class})
public class SellerExceptionHandler {

    @ExceptionHandler(SellerNotExistException.class)
    public ResponseEntity<ErrorMessage> sellerNotExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(SellerAlreadyVerifiedException.class)
    public ResponseEntity<ErrorMessage> sellerAlreadyVerifiedExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(SellerNotVerifiedException.class)
    public ResponseEntity<ErrorMessage> sellerNotVerifiedExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(SellerPasswordNotMatchException.class)
    public ResponseEntity<ErrorMessage> sellerPasswordNotMatchExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

}
