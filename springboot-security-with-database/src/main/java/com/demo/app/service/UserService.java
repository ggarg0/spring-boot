package com.demo.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.demo.app.exception.UserNotFoundException;
import com.demo.app.model.User;
import com.demo.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
		Optional<User> userInfo = userRepository.findByUsername(username);
		return userInfo.orElseThrow(() -> new UserNotFoundException("User details not found" + " : " + username));
	}

	public Collection<GrantedAuthority> getGrantedAuthority(User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole().equalsIgnoreCase("ADMIN"))
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		if (user.getRole().equalsIgnoreCase("DEVELOPER"))
			authorities.add(new SimpleGrantedAuthority("ROLE_DEVELOPER"));

		return authorities;
	}
	
	public List<User> retrieveAllUsers() {
		List<User> result = new ArrayList<>();
		this.userRepository.findAll().forEach(user -> {
			result.add(user);
		});
		return result;
	}

}
