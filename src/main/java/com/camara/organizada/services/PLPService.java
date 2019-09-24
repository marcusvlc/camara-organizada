package com.camara.organizada.services;

import java.util.Calendar;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.LegislativeDto;
import com.camara.organizada.models.PLP;
import com.camara.organizada.models.User;
import com.camara.organizada.repositories.PLPRepository;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.utils.Util;

@Service
public class PLPService {
	
	@Autowired
	private PLPRepository plpRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private Util utils = new Util();
	
	
	public PLP registerPLP(LegislativeDto payload) throws ServletException {
		
		PLP newPLP = convertToEntity(payload);
		
		return plpRepo.save(newPLP);
	}
	
	private PLP convertToEntity(LegislativeDto payload) throws ServletException {
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
		
		PLP isRegistedPLP = plpRepo.findById(payload.getCode()).orElse(null);
		
		if(isRegistedPLP != null) {
			throw new ServletException("Já existe uma PLP com esse código!");
		}
		
		
		PLP newPLP = new PLP(payload.getYear(), payload.getCode(), payload.getSummary(), payload.getInterests(), payload.getDocumentAddress(), payload.getArticle(), isValidUser.getDeputy());
		
		return newPLP;
	}

}
