package com.MyMemory.MyMemory.Controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Map<String, Object> errorBody(String message, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
        return new ResponseEntity<>(errorBody(ex.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity<>(errorBody(msg, HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return new ResponseEntity<>(errorBody(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
