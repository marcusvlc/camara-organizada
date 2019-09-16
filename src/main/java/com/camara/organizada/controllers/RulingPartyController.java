package com.camara.organizada.controllers;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.RulingParty;
import com.camara.organizada.services.RulingPartyService;

@RestController
@RequestMapping("/rulingparty")
public class RulingPartyController {
	
	@Autowired
	private RulingPartyService rulingPartyService;
	
	@PostMapping("/")
	public ResponseEntity<RulingParty> registerRulingParty(@RequestBody RulingParty rulingParty) throws ServletException{
		
		RulingParty u = rulingPartyService.registerRulingParty(rulingParty);
		
		return new ResponseEntity<RulingParty>(u, HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<RulingParty>> showRulingParty() {
		
		List<RulingParty> parties =  rulingPartyService.getRulingParties();
		
		return new ResponseEntity<List<RulingParty>>(parties, HttpStatus.OK);
		
	}

}
