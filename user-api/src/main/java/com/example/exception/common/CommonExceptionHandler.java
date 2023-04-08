package com.example.exception.common;

import com.example.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

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
