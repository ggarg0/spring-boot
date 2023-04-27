package com.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
