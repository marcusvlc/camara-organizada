package com.camara.organizada.commission;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.voting.VotingDto;

@RestController
@RequestMapping("/commission")
public class CommissionController {
	
	@Autowired
	private CommissionService commissionService;
	
	@PostMapping("/")
	public ResponseEntity<Commission> registerCommission(@RequestBody CommissionDto payload) throws ServletException {
		
		Commission c = commissionService.registerCommission(payload);
		
		return new ResponseEntity<Commission>(c, HttpStatus.OK);
	}

	@PostMapping("/{theme}")
	public ResponseEntity<Commission> voting(@PathVariable String theme, @RequestBody VotingDto voting) throws ServletException {
		
		Commission c = commissionService.comissionVoting(theme, voting);
		
		return new ResponseEntity<Commission>(c, HttpStatus.CREATED);
	}
}
