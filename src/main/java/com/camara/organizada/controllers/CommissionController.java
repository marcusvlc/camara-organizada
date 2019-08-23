package com.camara.organizada.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Commission;

@RestController
@RequestMapping("/commission")
public class CommissionController {
	
	@PostMapping("/register")
	public ResponseEntity<Commission> registerCommission(@RequestBody Commission commission) {
		Commission c = new Commission();
		
		return new ResponseEntity<Commission>(c, HttpStatus.OK);
	}

}
