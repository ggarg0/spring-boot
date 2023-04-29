package com.demo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		// User Role
		UserDetails theUser = User.withUsername("user")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("pass")
				.roles("USER").build();

		// Manager Role
		UserDetails theManager = User.withUsername("admin")
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("pass")
				.roles("ADMIN").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(theUser);
		userDetailsManager.createUser(theManager);

		return userDetailsManager;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
		.requestMatchers("/admin").hasRole("ADMIN")
		.requestMatchers("/user").hasAnyRole("USER", "ADMIN")
		.anyRequest().authenticated().and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

}
