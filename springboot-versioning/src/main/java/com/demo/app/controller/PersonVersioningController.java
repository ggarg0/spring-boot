package com.demo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.model.Name;
import com.demo.app.model.PersonV1;
import com.demo.app.model.PersonV2;

@RestController
public class PersonVersioningController {

	// Versioning using URL
	@GetMapping("v1/person")
	public PersonV1 personv1() {
		return new PersonV1("Gary Cook");
	}

	@GetMapping("v2/person")
	public PersonV2 personv2() {
		return new PersonV2(new Name("Tom", "Cook"));
	}

	// Versioning using Request Parameter
	@GetMapping(value = "/person/param", params = "version=1")
	public PersonV1 personV1() {
		return new PersonV1("Tom Bundle");
	}

	@GetMapping(value = "/person/param", params = "version=2")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Tom", "Warne"));
	}

	// Versioning using Request Header
	@GetMapping(value = "/person/header", headers = "X-API-Version=1")
	public PersonV1 headerV1() {
		return new PersonV1("Tom Maddy");
	}

	@GetMapping(value = "/person/header", headers = "X-API-Version=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Shane", "Cruise"));
	}
	
	//Versioning using Accept Header
	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Tom Waugh");
	}

	@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Tim", "Cruise"));
	}

}
