package com.example.springbootdatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootdatabase.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
