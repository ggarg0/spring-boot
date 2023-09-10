package com.cache.demo.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Please provide a title")
	private String title;

	@NotBlank(message = "Please provide a description")
	private String description;
	private String published;

	public Tutorial() {

	}

	public Tutorial(long id, String title, String description, String published) {

		this.id = id;
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public Tutorial(String title, String description, String published) {
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String isPublished() {
		return published;
	}

	public void setPublished(String isPublished) {
		this.published = isPublished;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

}