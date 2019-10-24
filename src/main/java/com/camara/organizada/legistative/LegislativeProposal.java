package com.camara.organizada.legistative;


import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.camara.organizada.deputy.Deputy;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class LegislativeProposal  {
	
//	@Transient
//	private static final long serialVersionUID = 1L;
	
	@ManyToOne()
	private Deputy author;
	
	private int year;
	
	@Id
	private String code;
	
	private String summary; // ementa
	
	private String interests;
	
	private ArrayList<String> currentSituation;
	
	private String documentAddress;
	
	public LegislativeProposal() {
		currentSituation = new ArrayList<String>();
	}

	public Deputy getAuthor() {
		return author;
	}

	public void setAuthor(Deputy author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}



	public ArrayList<String> getCurrentSituation() {
		return currentSituation;
	}

	public void setCurrentSituation(ArrayList<String> currentSituation) {
		this.currentSituation = currentSituation;
	}

	public String getDocumentAddress() {
		return documentAddress;
	}

	public void setDocumentAddress(String documentAddress) {
		this.documentAddress = documentAddress;
	}
	
	
	
	

}
