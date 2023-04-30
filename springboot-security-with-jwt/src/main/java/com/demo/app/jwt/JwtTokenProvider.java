package com.demo.app.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${jwt.token-validity-milliseconds}")
	private long validityInMilliseconds;

	private Key secret;

	private Key getSigningKey() {
		if (secret == null) {
			secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		}
		return secret;
	}

	public String generateToken(String username) {
		Claims claims = Jwts.claims().setSubject(username);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds)).signWith(getSigningKey())
				.compact();
	}

	public Boolean validateToken(String token, String usernameFromHeader, UserDetails userDetails) {
		return (userDetails != null && usernameFromHeader.equalsIgnoreCase(userDetails.getUsername())
				&& !isTokenExpired(token));
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Claims getClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String refreshJWTToken(String username, String tokenFromHeader, String forceRefresh) {
		String refreshToken = "";
		try {

			if ("Yes".equalsIgnoreCase(forceRefresh))
				return generateToken(username);

			long differenceInMinutes = (extractExpiration(tokenFromHeader).getTime() - new Date().getTime()) / 60000;
			if (differenceInMinutes < 15) {
				logger.info("JWT token refresh for " + username);
				return generateToken(username);
			} else {
				return tokenFromHeader;
			}
		} catch (Exception e) {
			logger.error("JWT token refresh for " + username + " : Exception - " + e.getMessage());
		}
		return refreshToken;
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public String extractUserrole(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
