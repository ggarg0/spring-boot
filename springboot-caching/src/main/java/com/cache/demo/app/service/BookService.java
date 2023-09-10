package com.cache.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cache.demo.app.exception.BookNotFoundException;
import com.cache.demo.app.model.Book;
import com.cache.demo.app.repository.BookRepository;

import jakarta.validation.constraints.Min;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Cacheable("books")
	public List<Book> findAll() {
		return repository.findAll();
	}

	public Book newBook(@RequestBody Book newBook) {
		return repository.save(newBook);
	}

	@Cacheable("book")
	public Book findOne(@PathVariable @Min(1) Long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	public Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
		Book book = repository.findById(id).map(x -> {
			x.setName(newBook.getName());
			x.setAuthor(newBook.getAuthor());
			x.setPrice(newBook.getPrice());
			return repository.save(x);
		}).orElseGet(() -> {
			newBook.setId(id);
			return repository.save(newBook);
		});
		return book;
	}

	public void deleteBook(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
