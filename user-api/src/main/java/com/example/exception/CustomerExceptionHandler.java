package com.example.exception;

import com.example.controller.SignUpController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = SignUpController.class)
@Slf4j
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotExistException.class)
    public ResponseEntity<ErrorMessage> CustomerNotExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> CustomerAlreadyExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomerAlreadyVerifiedException.class)
    public ResponseEntity<ErrorMessage> CustomerAlreadyVerifiedExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ExceedVerificationExpiredAtException.class)
    public ResponseEntity<ErrorMessage> ExceedVerificationExpiredAtExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(WrongVerificationCodeException.class)
    public ResponseEntity<ErrorMessage> WrongVerificationCodeExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }
}
