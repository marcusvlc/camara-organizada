package com.camara.organizada.services;

import java.util.Calendar;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.LegislativePLDto;
import com.camara.organizada.models.PL;
import com.camara.organizada.models.User;
import com.camara.organizada.repositories.PLRepository;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.utils.Util;

@Service
public class PLService {
	
	@Autowired
	private PLRepository plRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private Util utils = new Util();
	
	
	public PL registerPL(LegislativePLDto payload) throws ServletException {
		
		PL newPL = convertToEntity(payload);
				
		return plRepo.save(newPL);
	}


	private PL convertToEntity(LegislativePLDto payload) throws ServletException {
		if(!utils.isValidString(payload.getAuthorDNI()) || !utils.isValidString(payload.getSummary()) || !utils.isValidString(payload.getInterests()) || !utils.isValidString(payload.getDocumentAddress())) {
			 throw new ServletException("Campos inválidos ou vazios!");
		}
		
		if(!utils.isValidDNI(payload.getAuthorDNI())) {
			throw new ServletException("DNI inválido");
		}
		
		User isValidUser = userRepo.findById(payload.getAuthorDNI()).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Não existe uma pessoa cadastrada com o DNI fornecido");
		}
		
		if(isValidUser.getDeputy() == null) {
			throw new ServletException("A pessoa com o dni: " + payload.getAuthorDNI() + " não é um deputado");
		}
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		int minYear = 1988;
		
		if(payload.getYear() < minYear || payload.getYear() > currentYear) {
			throw new ServletException("O valor do ano deve ser maior que 1988 e menor ou igual  ao ano atual.");
		}
		
		PL isRegistedPL = plRepo.findById(payload.getCode()).orElse(null);
		
		if(isRegistedPL != null) {
			throw new ServletException("Já existe uma PL com esse código!");
		}
		
		
		PL newPL = new PL(payload.getYear(), payload.getCode(), payload.getSummary(), payload.getInterests(), payload.getCurrentSituation(), payload.getDocumentAddress(), payload.isProcessing(), isValidUser.getDeputy());
		
		return newPL;
	}

}
