package com.camara.organizada.legistative;

import java.util.ArrayList;
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
	public ResponseEntity<PL> registerPL(@RequestBody LegislativePLDto payload) throws ServletException {
		
		PL pl = plService.registerPL(payload);
		
		return new ResponseEntity<PL>(pl, HttpStatus.CREATED);
	}
	
	@PostMapping("/pec")
	public ResponseEntity<PEC> registerPEC(@RequestBody LegislativeDto payload) throws ServletException {
				
		PEC pec = pecService.registerPEC(payload);
		
		return new ResponseEntity<PEC>(pec, HttpStatus.CREATED);
	}
	
	@PostMapping("/plp")
	public ResponseEntity<PLP> registerPLP(@RequestBody LegislativeDto payload) throws ServletException {
		
		
		PLP plp = plpService.registerPLP(payload);
		
		return new ResponseEntity<PLP>(plp, HttpStatus.CREATED);
	}
	
	@GetMapping("/processing/{code}")
	public ResponseEntity<ArrayList<String>> showProcessing(@PathVariable String code) throws ServletException {
		
		 ArrayList<String> processing = legislativeService.showProcessing(code);
		
		return new ResponseEntity<ArrayList<String>>(processing, HttpStatus.ACCEPTED);
	}
	


}
