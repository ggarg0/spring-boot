package com.cache.demo.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cache.demo.app.model.Book;

import jakarta.validation.constraints.Min;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByName(@Min(1) String name);
}