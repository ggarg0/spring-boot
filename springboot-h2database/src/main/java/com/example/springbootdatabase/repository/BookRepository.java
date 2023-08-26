package com.example.springbootdatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootdatabase.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}