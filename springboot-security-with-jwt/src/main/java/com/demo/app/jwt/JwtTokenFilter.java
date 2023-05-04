package com.demo.app.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.app.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService appService;

	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, RuntimeException {

		((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Credentials", "true");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		((HttpServletResponse) response).setHeader("Access-Control-Max-Age", "3600");
		((HttpServletResponse) response).setHeader("Access-Control-Allow-Headers",
				"Access-Control-Allow-Headers, Origin, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Accept, X-Requested-With, remember-me, Authorization, Username, Role");

		String usernameFromHeader = ((HttpServletRequest) request).getHeader("Username");

		try {
			final String auth = ((HttpServletRequest) request).getHeader("Authorization");
			final String token = auth == null || auth.contains("null") ? null : auth.split(" ", 2)[1];
			if (null != token && null != usernameFromHeader && !tokenProvider.isTokenExpired(token)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = appService.loadUserByUsername(tokenProvider.extractUsername(token));

				if (!(userDetails != null && usernameFromHeader.equalsIgnoreCase(userDetails.getUsername())))
					throw new Exception(userDetails.getUsername() + "User name mismatch found in token");

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				if (usernamePasswordAuthenticationToken.isAuthenticated()) {
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					if (logRequestedURL((HttpServletRequest) request)) {
						if (userDetails.getUsername() != null) {
							logger.info("JwtTokenFilter : User {} and requested url : {}",
									tokenProvider.extractUsername(token), ((HttpServletRequest) request).getRequestURL());
						}
					}
				}
			}
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			if (e instanceof ExpiredJwtException) {
				logger.error("Session expired for user {}", usernameFromHeader);
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				logger.error("User {} invalid token exception : {} ", usernameFromHeader, e.getMessage());
				((HttpServletResponse) response).setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
			return;
		}
		filterChain.doFilter((HttpServletRequest) request, (HttpServletResponse) response);
	}

	public boolean logRequestedURL(HttpServletRequest req) {
		return !req.getRequestURL().toString().contains("refreshjwttoken");
	}
}
