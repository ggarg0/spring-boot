package com.cache.demo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCachingApplication.class, args);
	}

}
