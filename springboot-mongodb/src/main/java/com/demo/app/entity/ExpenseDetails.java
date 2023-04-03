package com.demo.app.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("expense")
public class ExpenseDetails {
	
	@Id
	private String id;
	
	@Field("name")
	@Indexed(unique = true)
	private String expenseName;
	
	@Field("category")
	private ExpenseCategory expenseCategory;
	
	@Field("amount")
	private BigDecimal expenseAmount;
	
}
