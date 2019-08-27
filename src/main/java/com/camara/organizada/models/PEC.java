package com.camara.organizada.models;

import javax.persistence.Entity;

@Entity
public class PEC extends LegislativeProposal {
	
	private String article;
	
	public PEC() {
		
	}
	
	public PEC(int year, String code, String summary, String interests, String documentAddress, String article, Deputy author ) {
		this.article = article;
		this.setAuthor(author);
		this.setYear(year);
		this.setCode(code);
		this.setSummary(summary);
		this.setInterests(interests);
		this.setDocumentAddress(documentAddress);
		this.setCurrentSituation("EM VOTACAO (CCJC)");

	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
	
	
	

}
