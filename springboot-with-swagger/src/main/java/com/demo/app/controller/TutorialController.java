package com.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.app.exception.BookNotFoundException;
import com.demo.app.model.Tutorial;
import com.demo.app.repository.TutorialRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tutorial", description = "Tutorial management APIs")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	private TutorialRepository repository;

	@Operation(summary = "Create a new Tutorial", tags = { "Post" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = repository.save(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Retrieve all Tutorials", tags = { "Get", "Filter" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "204", description = "There are no Tutorials", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials() {
		try {
			List<Tutorial> tutorials = repository.findAll();
			if (tutorials.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Retrieve a Tutorial by Id", description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.", tags = {
			"Get" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		try {
			Tutorial tutorial = repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
			if (tutorial != null)
				return new ResponseEntity<>(tutorial, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Update a Tutorial by Id", tags = { "Put" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {

		try {
			Tutorial _tutorial = repository.findById(id).map(x -> {
				x.setTitle(tutorial.getTitle());
				x.setDescription(tutorial.getDescription());
				x.setPublished(tutorial.isPublished());
				return repository.save(x);
			}).orElseGet(() -> {
				tutorial.setId(id);
				return repository.save(tutorial);
			});

			if (_tutorial != null)
				return new ResponseEntity<>(_tutorial, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Delete a Tutorial by Id", tags = { "Delete" })
	@ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Delete all Tutorials", tags = { "Delete" })
	@ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}