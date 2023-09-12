package com.cache.demo.app.exception;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(Object id) {
		super("Book not found : " + id);
	}

}