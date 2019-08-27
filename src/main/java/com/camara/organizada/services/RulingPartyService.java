package com.camara.organizada.services;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.RulingParty;
import com.camara.organizada.repositories.RulingPartyRepository;
import com.camara.organizada.utils.Util;

@Service
public class RulingPartyService {

	private Util util = new Util();
	@Autowired
	private RulingPartyRepository rulingPartyRepo;
	
	public RulingParty registerRulingParty(RulingParty rulingParty) throws ServletException {
		if(!util.isValidString(rulingParty.getPartyName())) {
			throw new ServletException("Campos invalidos!");
		}
		RulingParty registredRulingParty = rulingPartyRepo.save(rulingParty);
		return registredRulingParty;
	}

	public List<RulingParty> getRulingParties() {
		List<RulingParty> allRulingParties = rulingPartyRepo.findAll();
		return allRulingParties;
	}

}
