package com.camara.organizada.controllers;

import java.util.ArrayList;
import java.util.Map;

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

import com.camara.organizada.models.LegislativeProposal;
import com.camara.organizada.models.PEC;
import com.camara.organizada.models.PL;
import com.camara.organizada.models.PLP;
import com.camara.organizada.services.LegislativeService;
import com.camara.organizada.services.PECService;
import com.camara.organizada.services.PLPService;
import com.camara.organizada.services.PLService;

@RestController
@RequestMapping("/legislative")
public class LegislativeController {
	
	@Autowired
	private PLService plService;
	
	@Autowired
	private PECService pecService;
	
	@Autowired
	private PLPService plpService;
	
	@Autowired
	private LegislativeService legislativeService;
	
	
	@PostMapping("/pl")
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
	
	@PostMapping("/pec")
	public ResponseEntity<PEC> registerPEC(@RequestBody Map<String, Object> payload) throws ServletException {
		
		int year = (int) payload.get("year");
		String code = (String) payload.get("code");
		String summary = (String) payload.get("summary");
		String interests = (String) payload.get("interests");
		String documentAddress = (String) payload.get("documentAddress");
		String article = (String) payload.get("article");
		String authorDNI = (String) payload.get("author");
		
		
		PEC pec = pecService.registerPEC(year, code, summary, interests , documentAddress, article, authorDNI);
		
		return new ResponseEntity<PEC>(pec, HttpStatus.CREATED);
	}
	
	@PostMapping("/plp")
	public ResponseEntity<PLP> registerPLP(@RequestBody Map<String, Object> payload) throws ServletException {
		
		int year = (int) payload.get("year");
		String code = (String) payload.get("code");
		String summary = (String) payload.get("summary");
		String interests = (String) payload.get("interests");
		String documentAddress = (String) payload.get("documentAddress");
		String article = (String) payload.get("article");
		String authorDNI = (String) payload.get("author");
		
		
		PLP plp = plpService.registerPLP(year, code, summary, interests , documentAddress, article, authorDNI);
		
		return new ResponseEntity<PLP>(plp, HttpStatus.CREATED);
	}
	
	@GetMapping("/processing/{code}")
	public ResponseEntity<ArrayList<String>> showProcessing(@PathVariable String code) throws ServletException {
		
		 ArrayList<String> processing = legislativeService.showProcessing(code);
		
		return new ResponseEntity<ArrayList<String>>(processing, HttpStatus.ACCEPTED);
	}
	


}
