package com.demo.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.app.entity.ExpenseDetails;
import com.demo.app.repository.ExpenseDetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseDetailsService {
	private final ExpenseDetailsRepository expenseDetailsRepository;

	public void addExpense(ExpenseDetails expense) {
		expenseDetailsRepository.insert(expense);
	}

	public void updateExpense(ExpenseDetails expense) {
		ExpenseDetails savedExpense = expenseDetailsRepository.findById(expense.getId()).orElseThrow(
				() -> new RuntimeException(String.format("Cannot Find Expense ID %s", expense.getId())));
		savedExpense.setExpenseName(expense.getExpenseName());
		savedExpense.setExpenseCategory(expense.getExpenseCategory());
		savedExpense.setExpenseAmount(expense.getExpenseAmount());

		expenseDetailsRepository.save(expense);
	}

	public ExpenseDetails getExpense(String name) {
		return expenseDetailsRepository.findByName(name)
				.orElseThrow(() -> new RuntimeException(String.format("Cannot Find Expense - %s", name)));
	}

	public List<ExpenseDetails> getAllExpenses() {
		return expenseDetailsRepository.findAll();
	}

	public void deleteExpense(String id) {
		expenseDetailsRepository.deleteById(id);
	}
}
