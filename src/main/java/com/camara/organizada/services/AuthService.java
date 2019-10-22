//package com.camara.organizada.services;
//
//import javax.servlet.ServletException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.camara.organizada.models.Admin;
//import com.camara.organizada.repositories.AdminRepository;
//
//@Service
//public class AuthService {
//	
//	@Autowired
//	private AdminRepository adminRepo;
//	
//	@Autowired
//	private TokenService tokenService;
//	
//	public String authenticate(Admin admin) throws ServletException {
//		
//		if(!adminRepo.existsById(admin.getLogin())) {
//			throw new ServletException("Não existe um admin registrado com esses dados!");
//		}
//		
//		Admin authAdmin = adminRepo.findById(admin.getLogin()).orElse(null);
//		
//		if(!admin.getPassword().equals(authAdmin.getPassword())) {
//			throw new ServletException("Dados incorretos!");
//		}
//		
//		String token = tokenService.generateToken(admin.getLogin());
//		
//		return token;
//	}
//
//}
