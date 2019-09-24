package com.camara.organizada.services;


import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.InterestListDto;
import com.camara.organizada.models.User;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.utils.Util;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	private Util util = new Util();
	
	public User registerUser(User user) throws ServletException {
		
		User isUserRegistred = userRepo.findById(user.getDni()).orElse(null);
		
		if(!util.isValidString(user.getDni()) || !util.isValidString(user.getName()) || !util.isValidString(user.getState())) {
			throw new ServletException("Campos invalidos!");

		}
		
		if(!util.isValidDNI(user.getDni())) {
			throw new ServletException("DNI Inválido! Utilize apenas numeros!");
		}
		
		if(isUserRegistred != null) {
			throw new ServletException("Usuário já registrado!");
		}
		
		if(user.getState().length() != 2) {
			throw new ServletException("Nome de estado inválido! Insira o formato de duas letras!");
		}
		
		User registredUser = userRepo.save(user);
		return registredUser;
	}

	public User findById(String dni) throws ServletException {
		
		if(!util.isValidDNI(dni)) {
			throw new ServletException("DNI invalido!");

		}
		
		User user = userRepo.findById(dni).orElse(null);
		
		return user;
	}

	public User updateInterestsList(String dni, InterestListDto interestsList) throws ServletException {
		if(!util.isValidDNI(dni)) {
			throw new ServletException("DNI invalido!");

		}
		
		ArrayList<String> userInterests = interestsList.getInterestsList();
		User user = userRepo.findById(dni).orElse(null);
		
		if( user == null) {
			throw new ServletException("Usuário não encontrado");
		}
		user.setInterestList(userInterests);
		User updatedUser = userRepo.save(user);
		
		return updatedUser;
	}


	
}
