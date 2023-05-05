package com.demo.app.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletResponse response) {
        String error = "Invalid arguments passed";
        return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> hanldeBookNotFoundException(BookNotFoundException ex, HttpServletResponse response) throws IOException {
        System.out.println("BookNotFoundException called");
        String error = "Book not found";
        return buildResponseEntity(new APIError(HttpStatus.NOT_FOUND, error, ex));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        System.out.println("constraintViolationException called");
        String error = "Duplicate entry found";
        return buildResponseEntity(new APIError(HttpStatus.CONFLICT, error, ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, HttpServletResponse response) throws IOException {
        System.out.println("handleException called");
        String error = "Exception occurred";
        return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
