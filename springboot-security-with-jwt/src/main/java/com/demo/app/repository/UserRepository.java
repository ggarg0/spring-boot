package com.demo.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User loadUserDetails(@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.role = :role")
	List<User> loadUserByRole(@Param("role") String role);

	Optional<User> findByUsername(String username);
}