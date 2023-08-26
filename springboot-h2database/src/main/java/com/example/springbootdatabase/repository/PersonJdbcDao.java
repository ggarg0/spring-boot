package com.example.springbootdatabase.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springbootdatabase.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
	}

	public List<Person> findAllPerson() {
		return jdbcTemplate.query("SELECT id, name, location FROM person",
				(rs, rowNum) -> new Person(rs.getInt("id"), rs.getString("name"), rs.getString("location"), null));
	}
}
