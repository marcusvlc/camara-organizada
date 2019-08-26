package com.camara.organizada.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class RulingParty {

	@Id
	@GeneratedValue
	private Long id;
	
	private String partyName;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
}
