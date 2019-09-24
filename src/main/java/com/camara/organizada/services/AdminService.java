package com.camara.organizada.services;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.Admin;
import com.camara.organizada.repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	public Admin registerAdmin(Admin admin) throws ServletException {
				
		if(adminRepo.existsById(admin.getLogin())) {
			throw new ServletException("Já existe um admin com esse login cadastrado!");
		}
		
		Admin registedAdmin = adminRepo.save(admin);	
			
		return registedAdmin;
	}

}
