package com.camara.organizada.services;

import javax.servlet.ServletException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
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
	
	
	public Deputy registerDeputy(Deputy deputy, String dni) throws ServletException {
		
		// FALTANDO VALIDACAO DE DATA DE INICIO DO MANDATO.
		
		if(!util.isValidDNI(dni)) {
			throw new ServletException("DNI inválido!");
		}
		
		if(deputy.getInitJob() == null) {
			throw new ServletException("Data de inicio do mandato invalida! Insira no formato DDMMYYY");
		}
		
		System.out.println(deputy.getInitJob());
		
		if(!util.isValidDate(deputy.getInitJob())) {
			throw new ServletException("Data de inicio do mandato invalida! Ela deve ser anterior ou igual a data atual");
		}
		
		User isValidUser = userRep.findById(dni).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Nenhuma pessoa foi encontrada no sistema com o DNI fornecido!");
		}
		
		if(isValidUser.getParty() == null || isValidUser.getParty().trim().isEmpty()) {
			throw new ServletException("Não é possível cadastrar uma pessoa sem partido como deputado!");

		}
		
		//isValidUser.setDeputy(deputy);
		deputy.setUser(isValidUser);
		//userRep.saveAndFlush(isValidUser);
		
		
		return deputyRep.save(deputy);
	}

}
