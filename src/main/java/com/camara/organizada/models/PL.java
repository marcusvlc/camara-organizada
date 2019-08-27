package com.camara.organizada.models;

import javax.persistence.Entity;

@Entity
public class PL extends LegislativeProposal {
	
	private boolean processing;
	
	public PL() {
	}
	
	public PL(int year, String code, String summary, String interests, String currentSituation, String documentAddress, boolean processing, Deputy author ) {
		this.processing = processing;
		this.setAuthor(author);
		this.setYear(year);
		this.setCode(code);
		this.setSummary(summary);
		this.setInterests(interests);
		this.setDocumentAddress(documentAddress);
		this.setCurrentSituation("EM VOTACAO (CCJC)");

		
	}

	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	
}
