package com.camara.organizada.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private final String TOKEN_KEY = "banana";
	
	
	public String generateToken(String adminLogin) {
		String token = Jwts.builder().
				setSubject(adminLogin).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis()
				+ 1 * 60 * 1000))
				.compact();
		
		return token;
	}

}
