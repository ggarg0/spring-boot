package com.demo.app.exception;

public class TutorialNotFound extends RuntimeException {

	public TutorialNotFound(Long id) {
		super("Tutorial not found : " + id);
	}

}
