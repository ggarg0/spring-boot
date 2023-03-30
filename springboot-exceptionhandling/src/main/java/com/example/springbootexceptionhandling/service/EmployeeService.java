package com.example.springbootexceptionhandling.service;

import org.springframework.stereotype.Service;

import com.example.springbootexceptionhandling.entity.Employee;
import com.example.springbootexceptionhandling.exception.EmployeeServiceException;

@Service
public class EmployeeService {

	public Employee getEmployee() throws EmployeeServiceException {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(2000);

		return emp;
	}

	public Employee getEmployeeNull() throws EmployeeServiceException {

		return null;
	}

	public Employee getEmployeeException() throws EmployeeServiceException {

		throw new EmployeeServiceException();
	}

}
