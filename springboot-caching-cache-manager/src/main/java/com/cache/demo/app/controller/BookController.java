package com.cache.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cache.demo.app.model.Book;
import com.cache.demo.app.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController()
@RequestMapping("/api")
@Validated
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	ResponseEntity<List<Book>> findAll() {
		List<Book> list = bookService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Book> newBook(@Valid @RequestBody Book newBook) {
		Book _book = bookService.newBook(newBook);
		return new ResponseEntity<>(_book, HttpStatus.CREATED);
	}

	@GetMapping("/books/{id}")
	ResponseEntity<Book> findOne(@PathVariable @Min(1) Long id) {
		Book _book = bookService.findOneById(id);
		if (_book != null)
			return new ResponseEntity<>(_book, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/books/{id}")
	ResponseEntity<Book> saveOrUpdate(@Valid @RequestBody Book newBook, @PathVariable Long id) {
		Book _book = bookService.saveOrUpdate(newBook, id);
		return new ResponseEntity<>(_book, HttpStatus.OK);
	}

	@DeleteMapping("/books/{id}")
	ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/books")
	public ResponseEntity<HttpStatus> deleteAllBooks() {
		bookService.deleteAllBook();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}