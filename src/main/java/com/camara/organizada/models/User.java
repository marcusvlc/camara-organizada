package com.camara.organizada.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name="User_Table")
public class User {
	
	@Id
	private String dni;
	private String name;
	private String state;
	private String party;
	private String[] interestList;
	
	public User() {
		
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String[] getInterestList() {
		return interestList;
	}
	public void setInterestList(String[] interestList) {
		this.interestList = interestList;
	}
	
	

}
