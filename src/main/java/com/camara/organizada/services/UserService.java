package com.camara.organizada.services;


import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.User;
import com.camara.organizada.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User registerUser(User user) throws ServletException {
		// logica de negocio, aprimorar
		
		User isUserRegistred = userRepo.findById(user.getDni()).orElse(null);
		
		if(isUserRegistred != null) {
			// fazer tratamento personalizado futuramente
			throw new ServletException("Usuário já registrado!");
		}
		
		
		User registredUser = userRepo.save(user);
		return registredUser;
	}

}
