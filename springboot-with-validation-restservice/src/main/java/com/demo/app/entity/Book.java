package com.demo.app.entity;

import com.demo.app.validator.Author;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Please provide a name")
	private String name;

	@Author
	@NotEmpty(message = "Please provide a author")
	private String author;

	@NotNull(message = "Please provide a price")
	@DecimalMin(value = "1.00", message = "Invalid price: Equals to zero or Less than zero")
	@DecimalMax(value = "100.00", message = "Invalid price: Exceeds 100")
	private Float price;

	public Book() {
	}

	public Book(Long id, String name, String author, Float price) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public Book(String name, String author, Float price) {
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author='" + author + '\'' + ", price=" + price
				+ '}';
	}
}