package com.demo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

	@GetMapping("/user")
	public String usersStatusCheck() {
		return "Authorized user";
	}

	@GetMapping("/admin")
	public String managersStatusCheck() {
		return "Authorized admin";
	}

}