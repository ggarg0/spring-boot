package com.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.exception.BookNotFoundException;
import com.demo.app.model.Book;
import com.demo.app.repository.BookRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController()
@RequestMapping("/api")
@Validated
public class BookController {

	@Autowired
	private BookRepository repository;

	
	@GetMapping("/books")
	ResponseEntity<List<Book>> findAll() {
		try {
			List<Book> list = repository.findAll();
			if (list.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/books")
	@ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<Book> newBook(@Valid @RequestBody Book newBook) {
		try {
			Book _book = repository.save(newBook);
			return new ResponseEntity<>(_book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/books/{id}")
	ResponseEntity<Book> findOne(@PathVariable @Min(1) Long id) {
		try {
			Book _book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
			if (_book != null)
				return new ResponseEntity<>(_book, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books/{id}")
	ResponseEntity<Book> saveOrUpdate(@Valid @RequestBody Book newBook, @PathVariable Long id) {
		try {
			Book _book = repository.findById(id).map(x -> {
				x.setName(newBook.getName());
				x.setAuthor(newBook.getAuthor());
				x.setPrice(newBook.getPrice());
				return repository.save(x);
			}).orElseGet(() -> {
				newBook.setId(id);
				return repository.save(newBook);
			});

			if (_book != null)
				return new ResponseEntity<>(_book, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}