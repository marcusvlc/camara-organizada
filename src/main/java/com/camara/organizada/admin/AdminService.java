package com.camara.organizada.admin;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
