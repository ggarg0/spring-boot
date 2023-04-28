package com.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.app.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}