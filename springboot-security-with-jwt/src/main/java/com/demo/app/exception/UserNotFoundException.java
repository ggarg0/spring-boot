package com.demo.app.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
