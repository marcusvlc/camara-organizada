package com.camara.organizada.legistative;

public class LegislativePLDto {

	private int year; 
	private String code;
	private String summary;
	private String interests;
	private String currentSituation;
	private String documentAddress;
	private boolean processing;
	private String author;
	
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
	public String getCurrentSituation() {
		return currentSituation;
	}
	public void setCurrentSituation(String currentSituation) {
		this.currentSituation = currentSituation;
	}
	public String getDocumentAddress() {
		return documentAddress;
	}
	public void setDocumentAddress(String documentAddress) {
		this.documentAddress = documentAddress;
	}
	public boolean isProcessing() {
		return processing;
	}
	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}
