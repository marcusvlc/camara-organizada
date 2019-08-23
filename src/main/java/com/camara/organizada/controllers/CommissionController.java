package com.camara.organizada.controllers;

import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Commission;
import com.camara.organizada.services.CommissionService;

@RestController
@RequestMapping("/commission")
public class CommissionController {
	
	@Autowired
	private CommissionService commissionService;
	
	@PostMapping("/register")
	public ResponseEntity<Commission> registerCommission(@RequestBody Map<String, Object> payload) throws ServletException {
		
		String initials = (String) payload.get("initials");
		String dnis = (String) payload.get("dnis");
		String theme = (String) payload.get("theme");
		
		Commission c = commissionService.registerCommission(initials, dnis, theme);
		
		return new ResponseEntity<Commission>(c, HttpStatus.OK);
	}

}