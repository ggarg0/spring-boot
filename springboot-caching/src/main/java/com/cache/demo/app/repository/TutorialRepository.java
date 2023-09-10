package com.cache.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cache.demo.app.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

}
