package com.camara.organizada.legistative;

public class LegislativeDto {

	private int year;
	private String code;
	private String summary;
	private String interests;
	private String documentAddress;
	private String article;
	private String authorDNI;
	
	
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
	public String getDocumentAddress() {
		return documentAddress;
	}
	public void setDocumentAddress(String documentAddress) {
		this.documentAddress = documentAddress;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getAuthorDNI() {
		return authorDNI;
	}
	public void setAuthorDNI(String authorDNI) {
		this.authorDNI = authorDNI;
	}
	
	
}
