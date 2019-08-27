package com.camara.organizada.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

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
	
	
	public Deputy registerDeputy(Map<String,Object> deputy, String dni) throws ServletException, ParseException{
		
		if(!util.isValidString((String) deputy.get("initJob"))) {
			throw new ServletException("Data de inicio do mandato invalida! Insira no formato DDMMYYY");
		}
		
		LocalDate dateParsed = util.parseString2Date((String) deputy.get("initJob"));
                 
		
		if(!util.isValidInitDate(dateParsed)) {
			throw new ServletException("Data de inicio do mandato invalida! Ela deve ser anterior ou igual a data atual");
		}
		
		if(!util.isValidDNI(dni)) {
			throw new ServletException("DNI inválido!");
		}
				
		User isValidUser = userRep.findById(dni).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Nenhuma pessoa foi encontrada no sistema com o DNI fornecido!");
		}
		
		if(isValidUser.getParty() == null || isValidUser.getParty().trim().isEmpty()) {
			throw new ServletException("Não é possível cadastrar uma pessoa sem partido como deputado!");

		}
		
		
		Deputy newDeputy = new Deputy(dateParsed, (int) deputy.get("approvedLaws")); 

		newDeputy.setUser(isValidUser);
		
		
		return deputyRep.save(newDeputy);
		
	}
	
	public Deputy findById(String dni) throws ServletException {
		
		User user = userRep.findById(dni).orElse(null);
		
		if(user == null) {
			throw new ServletException("Não existe um usuario com o DNI fornecido!");
		} else {
			if(user.getDeputy() == null) {
				throw new ServletException("O DNI fornecido pertence a um usuário que não é deputado");

			}
		}
		
		Deputy deputy = user.getDeputy();
		
		return deputy;
	}
	
	

}
