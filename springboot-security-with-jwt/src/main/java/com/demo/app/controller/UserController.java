package com.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.app.jwt.JwtTokenProvider;
import com.demo.app.model.User;
import com.demo.app.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/api/users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}

	@PostMapping(value = "/api/users/authenticate")
	public User authenticate(@RequestBody User user) {
		User userAuth = new User();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			userAuth = (User) authentication.getPrincipal();
			userAuth.setPassword("");
			userAuth.setMessage(jwtTokenProvider.generateToken(userAuth.getUsername()));
		}
		return userAuth;
	}
}
