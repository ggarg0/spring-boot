package com.example.springbootdatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springbootdatabase.Jdbc.PersonJdbcDao;

@SpringBootApplication
public class SpringbootDatabaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDatabaseApplication.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDao personDao;

	@Override
	public void run(String... args) throws Exception {
		logger.info("ALl Person Details :  " + personDao.findAll());
		logger.info("ALl Person Details :  " + personDao.findAllPerson());
	}

}
