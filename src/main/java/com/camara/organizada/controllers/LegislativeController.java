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

import com.camara.organizada.models.PL;
import com.camara.organizada.services.PLService;

@RestController
@RequestMapping("/legislative")
public class LegislativeController {
	
	@Autowired
	private PLService plService;
	
	
	@PostMapping("/register/pl")
	public ResponseEntity<PL> registerPL(@RequestBody Map<String, Object> payload) throws ServletException {
		
		int year = (int) payload.get("year");
		String code = (String) payload.get("code");
		String summary = (String) payload.get("summary");
		String interests = (String) payload.get("interests");
		String currentSituation = (String) payload.get("currentSituation");
		String documentAddress = (String) payload.get("documentAddress");
		boolean processing = (boolean) payload.get("processing");
		String authorDNI = (String) payload.get("author");
		
		
		PL pl = plService.registerPL(year, code, summary, interests, currentSituation, documentAddress, processing, authorDNI);
		
		return new ResponseEntity<PL>(pl, HttpStatus.CREATED);
	}

}
