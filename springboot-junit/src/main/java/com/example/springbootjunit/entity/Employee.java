package com.example.springbootjunit.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {

	@Autowired
	public Employee() {
	}
	
	public Employee(String name, int monthlySalary, int age) {
		super();
		this.name = name;
		this.monthlySalary = monthlySalary;
		this.age = age;
	}

	private String name;
	private int monthlySalary;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Employee getEmployee() {
		return new Employee("getNAme",1000,32);
	}
}