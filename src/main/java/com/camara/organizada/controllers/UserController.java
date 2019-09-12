package com.camara.organizada.controllers;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.User;
import com.camara.organizada.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws ServletException{
		
		User u = userService.registerUser(user);
		
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/{dni}")
	public ResponseEntity<User> registerUser(@PathVariable String dni, @RequestBody Map<String,ArrayList<String>> interestsList) throws ServletException{
		
		User user = userService.updateInterestsList(dni, interestsList.get("interestsList"));
		
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<User> getUser(@PathVariable String dni) throws ServletException{
		User user = userService.findById(dni);
		
		if (user != null) { 
			user.setDeputy(null);
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

}
