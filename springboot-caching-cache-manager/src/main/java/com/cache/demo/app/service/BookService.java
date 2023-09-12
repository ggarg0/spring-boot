package com.cache.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

	@Cacheable(value = "books")
	public List<Book> findAll() {
		System.out.println("Calling findAll from service");
		return repository.findAll();
	}

	@CachePut(value = { "book" }, key = "#newBook.id")
	public Book newBook(@RequestBody Book newBook) {
		System.out.println("Calling newBook from service");
		return repository.save(newBook);
	}

	@Cacheable(value = "book", key = "#id", condition = "#id > 2", unless = "#result.price < 40")
	public Book findOneById(@PathVariable @Min(1) Long id) {
		System.out.println("Calling findOneById from service for Id : " + id);
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	@CachePut(value = { "book" }, key = "#newBook.id")
	public Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {
		System.out.println("Calling saveOrUpdate from service");
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

	@Caching(evict = { @CacheEvict(value = "books", key = "#id"), @CacheEvict(value = "book", key = "#id") })
	public void deleteBook(@PathVariable Long id) {
		System.out.println("Calling deleteBook from service");
		repository.deleteById(id);
	}

	@CacheEvict(value = { "book", "books" }, allEntries = true)
	public void deleteAllBook() {
		System.out.println("Calling deleteAllBook from service");
		repository.deleteAll();
	}
}
