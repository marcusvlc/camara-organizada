package com.camara.organizada.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Deputy;
import com.camara.organizada.models.User;
import com.camara.organizada.services.DeputyService;


@RestController
@RequestMapping("/deputy")
public class DeputyController {
	
	@Autowired
	private DeputyService deputyService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Deputy> registerDeputy(@RequestBody Deputy deputy) throws ServletException{
		
		Deputy registredDeputy = deputyService.registerDeputy(deputy);
		
		return new ResponseEntity<Deputy>(registredDeputy, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<Deputy> exibirPessoa(@PathVariable String dni) throws ServletException{
		Deputy deputy = deputyService.findById(dni);
		if(deputy != null) {
			return new ResponseEntity<Deputy>(deputy, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<Deputy>(deputy, HttpStatus.NOT_FOUND);
		}
		
	}

}
