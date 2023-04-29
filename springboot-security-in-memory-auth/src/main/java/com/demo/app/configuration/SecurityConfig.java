package com.demo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user")
				.passwordEncoder(bCryptPasswordEncoder()::encode).password("pass")
				.roles("USER").build();

		UserDetails admin = User.withUsername("admin")
				//.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("pass")
				.passwordEncoder(bCryptPasswordEncoder()::encode).password("pass")
				.roles("ADMIN").build();

		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		userDetailsManager.createUser(user);
		userDetailsManager.createUser(admin);

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
	
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

}
