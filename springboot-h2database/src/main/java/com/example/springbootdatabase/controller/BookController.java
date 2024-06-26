package com.example.springbootdatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootdatabase.entity.Book;
import com.example.springbootdatabase.repository.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository repository;

	@GetMapping("/books")
	List<Book> findAll() {
		return repository.findAll();
	}

	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}

	@GetMapping("/books/{id}")
	Book findOne(@PathVariable Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception());
	}

	@PutMapping("/books/{id}")
	Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
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