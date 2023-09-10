package com.cache.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.cache.demo.app.exception.BookNotFoundException;
import com.cache.demo.app.model.Tutorial;
import com.cache.demo.app.repository.TutorialRepository;

@Service
public class TutorialService {

	@Autowired
	private TutorialRepository repository;

	public List<Tutorial> getAllTutorials() {
		return repository.findAll();
	}

	public Tutorial getTutorialById(@PathVariable("id") long id) {
		return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	public void deleteTutorial(@PathVariable("id") long id) {
		repository.deleteById(id);
	}

	public void deleteAllTutorials() {
		repository.deleteAll();
	}
}
