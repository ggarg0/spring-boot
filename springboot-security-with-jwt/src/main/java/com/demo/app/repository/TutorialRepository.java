package com.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.app.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

}
