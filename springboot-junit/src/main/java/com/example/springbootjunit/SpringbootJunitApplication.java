package com.example.springbootjunit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.springbootjunit.entity.Employee;
import com.example.springbootjunit.service.EmployeeService;

@SpringBootApplication
public class SpringbootJunitApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringbootJunitApplication.class, args);

		try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringbootJunitApplication.class)) {
			EmployeeService empLogic = applicationContext.getBean(EmployeeService.class);
			Employee emp = applicationContext.getBean(Employee.class).getEmployee();
			System.out.println("Salary : " + empLogic.calculateYearlySalary(emp));
			System.out.println("Employee Details : " + emp.toString());
		}
	}
}
