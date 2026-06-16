package com.example.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFound(
            StudentNotFoundException ex) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "message",
                        ex.getMessage()
                ));
    }
}