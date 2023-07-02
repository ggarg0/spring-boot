package com.example.springbootexceptionhandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootexceptionhandling.entity.Employee;
import com.example.springbootexceptionhandling.exception.EmployeeServiceException;
import com.example.springbootexceptionhandling.exception.ResourceNotFoundException;
import com.example.springbootexceptionhandling.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee getEmpl() throws ResourceNotFoundException, EmployeeServiceException {
		Employee emp = employeeService.getEmployee();

		if (emp == null)
			throw new ResourceNotFoundException("Employee not found");

		return emp;
	}

	@RequestMapping(value = "/employee2", method = RequestMethod.GET)
	public Employee getEmp2() throws ResourceNotFoundException, EmployeeServiceException {
		Employee emp = employeeService.getEmployeeNull();
		if (emp == null)
			throw new ResourceNotFoundException("Employee not found");

		return emp;
	}

	@RequestMapping(value = "/employee3", method = RequestMethod.GET)
	public Employee getEmp3() throws ResourceNotFoundException, EmployeeServiceException {
		Employee emp = employeeService.getEmployeeException();
		if (emp == null)
			throw new ResourceNotFoundException("Employee not found");

		return emp;
	}

	@RequestMapping(value = "/employee4", method = RequestMethod.GET)
	public Employee getEm4() throws Exception {
		Employee emp = employeeService.getEmployeeNull();
		if (emp == null)
			throw new Exception("Generic employee exception");

		return emp;
	}
}
