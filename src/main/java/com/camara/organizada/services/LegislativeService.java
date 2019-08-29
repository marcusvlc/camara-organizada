package com.camara.organizada.services;

import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.LegislativeProposal;

@Service
public class LegislativeService {
	
	@Autowired
	private CommissionService commissionService;
	
	
	public ArrayList<String> showProcessing(String code ) throws ServletException {
		
		LegislativeProposal prop = commissionService.findPL(code);
		
		if(prop == null) {
			throw new ServletException("Nao existe nenhuma proposta de lei com esse codigo!");
		}
		
		
		return prop.getCurrentSituation();
	}

}
