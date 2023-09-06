package com.demo.app.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.model.Greeting;

@RestController
public class DemoController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Value("${app.message}")
	private String welcomeMessage;

	@GetMapping("/hello")
	@ResponseBody
	public Greeting sayHello(@RequestParam(name = "name", required = true, defaultValue = "Stranger") String name) {
		logger.info("Fetching greeting details : " + name);
		return new Greeting(counter.incrementAndGet(), String.format(welcomeMessage + ", %s!", name));
		//return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
