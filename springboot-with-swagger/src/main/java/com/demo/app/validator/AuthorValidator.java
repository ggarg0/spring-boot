package com.demo.app.validator;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorValidator implements ConstraintValidator<Author, String> {

	List<String> authors = Arrays.asList("Java", "Scala", "Angular", "Node");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return authors.contains(value);
	}

}
