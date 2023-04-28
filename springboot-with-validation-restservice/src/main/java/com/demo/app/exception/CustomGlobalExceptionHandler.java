package com.demo.app.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
	}
	*/
	
	@ExceptionHandler(BookNotFoundException.class)
	public void springHandleNotFound(HttpServletResponse response) throws IOException {
		System.out.println("BookNotFoundException called");
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public void constraintViolationException(HttpServletResponse response) throws IOException {
		System.out.println("constraintViolationException called");
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(HttpServletResponse response) throws IOException {
		System.out.println("handleException called");
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
