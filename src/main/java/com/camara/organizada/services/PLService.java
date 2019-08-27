package com.camara.organizada.services;

import java.util.Calendar;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public PL registerPL(int year, String code, String summary, String interests, String currentSituation, String documentAddress, boolean processing, String authorDNI) throws ServletException {
		
		if(!utils.isValidString(authorDNI) || !utils.isValidString(summary) || !utils.isValidString(interests) || !utils.isValidString(documentAddress)) {
			 throw new ServletException("Campos inválidos ou vazios!");
		}
		
		if(!utils.isValidDNI(authorDNI)) {
			throw new ServletException("DNI inválido");
		}
		
		User isValidUser = userRepo.findById(authorDNI).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Não existe uma pessoa cadastrada com o DNI fornecido");
		}
		
		if(isValidUser.getDeputy() == null) {
			throw new ServletException("A pessoa com o dni: " + authorDNI + " não é um deputado");
		}
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		int minYear = 1988;
		
		if(year < minYear || year > currentYear) {
			throw new ServletException("O valor do ano deve ser maior que 1988 e menor ou igual  ao ano atual.");
		}
		
		PL isRegistedPL = plRepo.findById(code).orElse(null);
		
		if(isRegistedPL != null) {
			throw new ServletException("Já existe uma PL com esse código!");
		}
		
		
		PL newPL = new PL(year, code, summary, interests, currentSituation, documentAddress, processing, isValidUser.getDeputy());
		
		
		
		return plRepo.save(newPL);
	}

}
