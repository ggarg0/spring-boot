package com.example.springbootjunit.service;

import org.springframework.stereotype.Component;

import com.example.springbootjunit.entity.Employee;

@Component
public class EmployeeService {

	public int calculateYearlySalary(Employee employeeDetails) {
		int yearlySalary = 0;
		yearlySalary = employeeDetails.getMonthlySalary() * 12;
		employeeDetails.setMonthlySalary(yearlySalary);
		return yearlySalary;
	}

	public int calculateAppraisal(Employee employeeDetails) {
		int appraisal = 0;
		if (employeeDetails.getMonthlySalary() < 10000) {
			appraisal = 500;
		} else {
			appraisal = 1000;
		}
		return appraisal;
	}
}