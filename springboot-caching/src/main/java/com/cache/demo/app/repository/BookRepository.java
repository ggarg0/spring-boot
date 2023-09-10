package com.cache.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cache.demo.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}