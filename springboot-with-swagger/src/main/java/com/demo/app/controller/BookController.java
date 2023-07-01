package com.demo.app.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.exception.BookNotFoundException;
import com.demo.app.model.Book;
import com.demo.app.repository.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Book Details", description = "Book details management APIs")
@RestController("/api")
@Validated
public class BookController {

	@Autowired
	private BookRepository repository;

	@Operation(summary = "Retrieve all Books", tags = { "Get", "Filter" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Book.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "204", description = "There are no Books", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

	@Operation(summary = "Add a new Book", tags = { "Post" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Book.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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

	@Operation(summary = "Retrieve a Book by Id", description = "Get a Book object by specifying its id.", tags = {
			"Get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Book.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/books/{id}")
	ResponseEntity<Book> findOne(@PathVariable @Min(1) Long id) {
		Book _book = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		if (_book != null)
			return new ResponseEntity<>(_book, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Operation(summary = "Update a Book by Id", tags = { "Put" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Book.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
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

	@Operation(summary = "Delete Book by id", tags = { "Delete" })
	@ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/books/{id}")
	ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}