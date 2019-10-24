package com.camara.organizada.deputy;

import java.text.ParseException;
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
@RequestMapping("/deputy")
public class DeputyController {
	
	@Autowired
	private DeputyService deputyService;
	
	
	@PostMapping("/user/{person_dni}")
	public ResponseEntity<Deputy> registerDeputy(@RequestBody DeputyDto deputyDto, @PathVariable String person_dni ) throws ServletException, ParseException{
		Deputy registredDeputy = deputyService.registerDeputy(deputyDto, person_dni);
		
		return new ResponseEntity<Deputy>(registredDeputy, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<Deputy> getDeputy(@PathVariable String dni) throws ServletException{
		Deputy deputy = deputyService.findById(dni);
		
		return new ResponseEntity<Deputy>(deputy, HttpStatus.FOUND);
			
	}

}
