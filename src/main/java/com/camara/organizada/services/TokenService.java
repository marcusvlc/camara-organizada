package com.camara.organizada.services;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	private final String TOKEN_KEY = "banana";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	
	public String generateToken(String adminLogin) {
		String token = Jwts.builder().
				setSubject(adminLogin).
				signWith(SignatureAlgorithm.HS512, TOKEN_KEY).
				setExpiration(new Date(System.currentTimeMillis()
				+ (1 * 60 * 60 * 1000)))
				.compact();
		
		return token;
	}


	public boolean isValidToken(HttpServletRequest request) {
		
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			
			String tokenContent = token.replace(TOKEN_PREFIX, "");
						
			if(tokenContent != null) {
				String adminLogin = Jwts.parser()
						.setSigningKey(TOKEN_KEY)
						.parseClaimsJws(tokenContent)
						.getBody()
						.getSubject();
				
				if(adminLogin != null) {
					return true;
				}
			}
			
		}
		
		return false;
	}

}
