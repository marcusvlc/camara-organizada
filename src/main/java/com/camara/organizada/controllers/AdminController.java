package com.camara.organizada.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.models.Admin;
import com.camara.organizada.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping()
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws ServletException {
		
		Admin registredAdmin = adminService.registerAdmin(admin);
		
		return new ResponseEntity<Admin>(registredAdmin, HttpStatus.CREATED);
		
	}
}
