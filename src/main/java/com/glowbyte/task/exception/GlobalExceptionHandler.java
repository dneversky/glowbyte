package com.glowbyte.task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvariantException.class)
    public ResponseEntity<String> handleApiException(InvariantException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleAnyExceptions(HttpMessageNotReadableException e) {
        if (e.getRootCause() instanceof InvariantException) {
            return handleApiException((InvariantException) e.getRootCause());
        }
        return ResponseEntity.status(406).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyExceptions(Exception e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
