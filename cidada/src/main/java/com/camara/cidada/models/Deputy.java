package com.camara.cidada.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Deputy {
	
	private String dni;
	private String name;
	private String state;
	private String party;
	private ArrayList<String> interestList;
	//private Date initJob;
	private int approvedLaws;
	
	public Deputy() {
		
	}
	
	public Deputy(String dni, String name, String state, String party, ArrayList<String> interestList, int approvedLaws) {
		this.dni = dni;
		this.name = name;
		this.state = state;
		this.party = party;
		this.interestList = interestList;
		//this.initJob = initJob;
		this.approvedLaws = approvedLaws;
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

	public void setInterestList(ArrayList<String> interestList) {
		this.interestList = interestList;
	}

//	public Date getInitJob() {
//		return initJob;
//	}
//
//	public void setInitJob(Date initJob) {
//		this.initJob = initJob;
//	}

	public int getApprovedLaws() {
		return approvedLaws;
	}

	public void setApprovedLaws(int approvedLaws) {
		this.approvedLaws = approvedLaws;
	}
	
	
	

}
