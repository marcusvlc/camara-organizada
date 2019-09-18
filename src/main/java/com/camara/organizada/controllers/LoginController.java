package com.camara.organizada.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Admin;
import com.camara.organizada.services.AuthService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	AuthService authService;
	
	
	@PostMapping()
	public LoginResponse authenticate(@RequestBody Admin admin) throws ServletException {
		
		String token = authService.authenticate(admin);
		
		return new LoginResponse(token);
		
		
	}
	
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}

}
