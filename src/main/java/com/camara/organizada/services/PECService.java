package com.camara.organizada.services;

import java.util.Calendar;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.LegislativeDto;
import com.camara.organizada.models.PEC;
import com.camara.organizada.models.PL;
import com.camara.organizada.models.User;
import com.camara.organizada.repositories.PECRepository;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.utils.Util;

@Service
public class PECService {
	
	@Autowired
	private PECRepository pecRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private Util utils = new Util();
	
	
	public PEC registerPEC(LegislativeDto payload) throws ServletException {
		PEC newPEC = convertToEntity(payload);	
		
		return pecRepo.save(newPEC);
	}


	private PEC convertToEntity(LegislativeDto payload) throws ServletException {
		if(!utils.isValidString(payload.getAuthorDNI()) || !utils.isValidString(payload.getSummary()) || !utils.isValidString(payload.getInterests()) || !utils.isValidString(payload.getDocumentAddress()) || !utils.isValidString(payload.getArticle())) {
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
		
		PEC isRegistedPEC = pecRepo.findById(payload.getCode()).orElse(null);
		
		if(isRegistedPEC != null) {
			throw new ServletException("Já existe uma PEC com esse código!");
		}
		
		
		PEC newPEC = new PEC(payload.getYear(), payload.getCode(), payload.getSummary(), payload.getInterests(), payload.getDocumentAddress(), payload.getArticle(), isValidUser.getDeputy());
		
	
		return newPEC;
	}

}
