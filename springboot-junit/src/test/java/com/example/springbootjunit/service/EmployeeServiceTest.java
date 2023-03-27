package com.example.springbootjunit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.springbootjunit.entity.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
	@InjectMocks
	EmployeeService empService;

	Employee employee;

	@Mock
	Employee employeeMock;

	@BeforeClass
	public static void beforeClass() {
		System.out.println("in before class");
	}

	@Before
	public void before() {
		System.out.println("Instantiating Object ... ");
		empService = new EmployeeService();
		employee = new Employee("Rajeev", 8000, 26);
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("in after class");
	}

	@After
	public void after() {
		System.out.println("Deleting Object ... ");
		empService = null;
		employee = null;
	}

	@Test
	@Timeout(3)
	public void testCalculateYearlySalary() {
		int salary = empService.calculateYearlySalary(employee);
		System.out.println("salary ... " + salary);
		assertEquals(96000, salary, 0.0);
	}

	@Ignore("Not Ready to Run")
	@Test
	public void testCalculateAppraisal() {
		int appraisal = empService.calculateAppraisal(employee);
		System.out.println("appraisal ... " + appraisal);
		assertEquals(500, appraisal, 0.0);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCalculateAppraisalWithMockito() {
		Employee emp = new Employee("GARG", 9000, 21);
		when(employeeMock.getEmployee()).thenReturn(emp);
		assertEquals(500, empService.calculateAppraisal(employeeMock.getEmployee()));
	}

	@Test
	public void assertMethods() {
		int salary = empService.calculateYearlySalary(employee);
		int appraisal = empService.calculateAppraisal(employee);

		assertTrue(salary > appraisal);
		// assertFalse(salary>appraisal);
		// assertNull(employee);
		assertNotNull(employee);
	}

}
