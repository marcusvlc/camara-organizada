package com.camara.organizada.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="User_Table")
public class User {
	
	@Id
	private String dni;
	private String name;
	private String state;
	private String party;
	private String[] interestList;
	
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
	public String[] getInterestList() {
		return interestList;
	}
	public void setInterestList(String[] interestList) {
		this.interestList = interestList;
	}
	
	

}
