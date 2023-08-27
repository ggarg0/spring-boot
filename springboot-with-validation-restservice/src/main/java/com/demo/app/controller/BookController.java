package com.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.entity.Book;
import com.demo.app.exception.BookNotFoundException;
import com.demo.app.repository.BookRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
public class BookController {

	@Autowired
	private BookRepository repository;

	@GetMapping("/books")
	List<Book> findAll() {
		return repository.findAll();
	}

	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	Book newBook(@Valid @RequestBody Book newBook) {
		return repository.save(newBook);
	}

	@GetMapping("/books/{id}")
	Book findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	@PutMapping("/books/{id}")
	Book saveOrUpdate(@Valid @RequestBody Book newBook, @PathVariable Long id) {
		System.out.println();
		return repository.findById(id).map(x -> {
			x.setName(newBook.getName());
			x.setAuthor(newBook.getAuthor());
			x.setPrice(newBook.getPrice());
			return repository.save(x);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
		
		
	}

	@DeleteMapping("/books/{id}")
	void deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	}
}