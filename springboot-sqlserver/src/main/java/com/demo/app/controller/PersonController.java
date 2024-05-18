package com.demo.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.model.Person;
import com.demo.app.repository.PersonRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonRepository repo;

	@GetMapping("/persons")
	@ResponseStatus(HttpStatus.OK)
	public List<Person> getAllPersons() {
		List<Person> list = repo.findAll();
		return list;
	}

	@PostMapping("/persons")
	public ResponseEntity<Person> createTutorial(@RequestBody Person dao) {

		Person entity = new Person();
		Person person = repo.save(entity);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}

}
