package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example")
@Slf4j
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotExistException.class)
    public ResponseEntity<ErrorMessage> customerNotExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> customerAlreadyExistExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(CustomerAlreadyVerifiedException.class)
    public ResponseEntity<ErrorMessage> customerAlreadyVerifiedExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ExceedVerificationExpiredAtException.class)
    public ResponseEntity<ErrorMessage> exceedVerificationExpiredAtExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(WrongVerificationCodeException.class)
    public ResponseEntity<ErrorMessage> wrongVerificationCodeExceptionHandler(
            Exception e
    ) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.of(e, HttpStatus.BAD_REQUEST));
    }
}
