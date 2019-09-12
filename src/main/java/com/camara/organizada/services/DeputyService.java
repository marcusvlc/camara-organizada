package com.camara.organizada.services;

import java.text.ParseException;
import java.time.LocalDate;


import javax.servlet.ServletException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.DeputyDto;
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
	
	
	public Deputy registerDeputy(DeputyDto deputyDto, String dni) throws ServletException, ParseException{
		
		
				
		User isValidUser = userRep.findById(dni).orElse(null);
		
		if(isValidUser == null) {
			throw new ServletException("Nenhuma pessoa foi encontrada no sistema com o DNI fornecido!");
		}
		
		if(isValidUser.getParty() == null || isValidUser.getParty().trim().isEmpty()) {
			throw new ServletException("Não é possível cadastrar uma pessoa sem partido como deputado!");

		}
		
		Deputy newDeputy = convertToEntity(deputyDto); 

		newDeputy.setUser(isValidUser);
		
		
		return deputyRep.save(newDeputy);
		
	}
	
	private Deputy convertToEntity(DeputyDto deputyDto) throws ServletException {
		
		
		if(!util.isValidString((String) deputyDto.getInitJob())) {
			throw new ServletException("Data de inicio do mandato invalida! Insira no formato DDMMYYY");
		}
		
		LocalDate dateParsed = util.parseString2Date((String) deputyDto.getInitJob());
                 
		
		if(!util.isValidInitDate(dateParsed)) {
			throw new ServletException("Data de inicio do mandato invalida! Ela deve ser anterior ou igual a data atual");
		}
		
		Deputy deputy = new Deputy(dateParsed, (int) deputyDto.getApprovedLaws());
		
		
		return deputy;
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
