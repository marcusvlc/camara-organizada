package com.camara.organizada.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Deputy;
import com.camara.organizada.services.DeputyService;

@RestController
@RequestMapping("/deputy")
public class DeputyController {
	
	@Autowired
	private DeputyService deputyService;
	
	
	@PostMapping("/register/{person_dni}")
	public ResponseEntity<Deputy> registerDeputy(@RequestBody Deputy deputy, @PathVariable String person_dni ) throws ServletException{
		System.out.println(person_dni);
		Deputy registredDeputy = deputyService.registerDeputy(deputy, person_dni);
		
		return new ResponseEntity<Deputy>(registredDeputy, HttpStatus.CREATED);
		
	}

}
