package com.camara.organizada.user;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.camara.organizada.deputy.Deputy;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="User_Table")
public class User {
	
	@Id
	private String dni;
	private String name;
	private String state;
	private String party;
	private ArrayList<String> interestList;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonBackReference
	private Deputy deputy;
	
	public User() {
		
	}
	
	
	
	public Deputy getDeputy() {
		return deputy;
	}



	public void setDeputy(Deputy deputy) {
		this.deputy = deputy;
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
	public ArrayList<String> getInterestList() {
		return interestList;
	}
	public void setInterestList(ArrayList<String> userInterests) {
		this.interestList = userInterests;
	}
	
	

}
