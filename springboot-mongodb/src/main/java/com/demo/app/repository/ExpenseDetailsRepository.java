package com.demo.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.demo.app.entity.ExpenseDetails;

public interface ExpenseDetailsRepository extends MongoRepository<ExpenseDetails, String> {
	@Query("{'name': ?0}")
	Optional<ExpenseDetails> findByName(String name);
}
