package com.camara.organizada.services;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.RulingParty;
import com.camara.organizada.repositories.RulingPartyRepository;
import com.camara.organizada.utils.Util;

@Service
public class RulingPartyService {

	private Util util = new Util();
	@Autowired
	private RulingPartyRepository rulingPartyRepo;
	
    @CacheEvict(cacheNames = "RullingParties", allEntries = true)
	public RulingParty registerRulingParty(RulingParty rulingParty) throws ServletException {
		if(!util.isValidString(rulingParty.getPartyName())) {
			throw new ServletException("Campos invalidos!");
		}
		
		if(rulingPartyRepo.existsById(rulingParty.getPartyName())) {
			throw new ServletException("Partido ja existente!");
		}
		RulingParty registredRulingParty = rulingPartyRepo.save(rulingParty);
		return registredRulingParty;
	}
	
	@Cacheable(cacheNames = "RullingParties", key="#parties")
	public List<RulingParty> getRulingParties() {
		List<RulingParty> allRulingParties = rulingPartyRepo.findAll();
		return allRulingParties;
	}

}
