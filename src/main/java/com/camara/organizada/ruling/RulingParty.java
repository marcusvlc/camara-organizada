package com.camara.organizada.ruling;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RulingParty_Table")
public class RulingParty {

	@Id
	private String partyName;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
}
