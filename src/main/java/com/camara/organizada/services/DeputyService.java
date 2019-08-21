package com.camara.organizada.services;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.Deputy;
import com.camara.organizada.models.User;
import com.camara.organizada.repositories.DeputyRepository;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.utils.Util;

@Service
public class DeputyService {
	
	@Autowired
	private DeputyRepository deputyRep;
	
	@Autowired
	private UserRepository userRep;
	
	private Util util = new Util();
	
	
	public Deputy registerDeputy(Deputy deputy) throws ServletException {
		
		// FALTANDO VALIDACAO DE DATA DE INICIO DO MANDATO.
		
		if(!util.isValidDNI(deputy.getDni())) {
			throw new ServletException("DNI inválido!");
		}
		
		User isValidUser = userRep.findById(deputy.getDni()).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Nenhuma pessoa foi encontrada no sistema com o DNI fornecido!");
		}
		
		Deputy isValidDeputy = deputyRep.findById(deputy.getDni()).orElse(null);
		
		if(isValidDeputy != null) {
			throw new ServletException("Já existe um deputado com esse DNI no sistema!");
		}
		
		if(isValidUser.getParty() == null || isValidUser.getParty().trim().isEmpty()) {
			throw new ServletException("Não é possível cadastrar uma pessoa sem partido como deputado!");

		}
		
		//deputy.setDni("123");
		
		
		return null;
	}
	
	public Deputy findById(String dni) throws ServletException {
		
		if(!util.isValidString(dni)) {
			throw new ServletException("DNI invalido!");

		}
		
		Deputy deputy = deputyRep.findById(dni).orElse(null);
		
		return deputy;
	}
	
	

}
