package com.demo.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.entity.ExpenseDetails;
import com.demo.app.service.ExpenseDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseDetailsController {
	private final ExpenseDetailsService expenseDetailsService;

	@PostMapping
	public ResponseEntity<ExpenseDetails> addExpense(@RequestBody ExpenseDetails expense) {
		expenseDetailsService.addExpense(expense);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping
	public ResponseEntity<ExpenseDetails> updateExpense(@RequestBody ExpenseDetails expense) {
		expenseDetailsService.updateExpense(expense);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	public ResponseEntity<List<ExpenseDetails>> getAllExpenses() {
		return ResponseEntity.ok(expenseDetailsService.getAllExpenses());
	}

	@GetMapping("/{name}")
	public ResponseEntity<ExpenseDetails> getExpenseByName(@PathVariable String name) {
		return ResponseEntity.ok(expenseDetailsService.getExpense(name));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ExpenseDetails> deleteExpense(@PathVariable String id) {
		expenseDetailsService.deleteExpense(id);
		return ResponseEntity.noContent().build();
	}
}
