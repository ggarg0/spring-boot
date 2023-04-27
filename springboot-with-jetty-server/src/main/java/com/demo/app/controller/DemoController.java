package com.demo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping(value = "/hello")
	public String hello() {
		System.out.println("Controller called");
		return "Hello World";
	}
}
