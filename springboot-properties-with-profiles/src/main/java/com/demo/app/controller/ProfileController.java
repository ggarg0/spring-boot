package com.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.configuration.DBConfiguration;

@RestController
public class ProfileController {
	@Value("${app.message}")
	private String welcomeMessage;
	
	@Autowired
	DBConfiguration config;
	
	@GetMapping("/hello")
	public String getDataBaseConnectionDetails() {
		System.out.println(config.toString());
		return welcomeMessage + "  :  " + config.toString();
	}
}
