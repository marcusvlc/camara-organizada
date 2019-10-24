package com.camara.organizada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camara.organizada.services.CacheService;

@RestController
public class CacheController {

	@Autowired
	CacheService cache;
	
	@GetMapping("/cache")
	public ResponseEntity<String> getMsg() {
		return new ResponseEntity<>(cache.cacheThis(), HttpStatus.OK);
	}
}
