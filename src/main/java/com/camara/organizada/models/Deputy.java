package com.camara.organizada.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Deputy {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate initJob;
	private int approvedLaws;
	
	@OneToOne()
	@JsonManagedReference
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany(mappedBy = "participants")
	@JsonBackReference
	private List<Commission> participations;
	
	
	public Deputy() {
		this.approvedLaws = 0;
		this.participations = new ArrayList<Commission>();
	}
	
	
	public Deputy(LocalDate initJob) {
		this.initJob = initJob;
		this.approvedLaws = 0;
		this.participations = new ArrayList<Commission>();
	}
	
	

	public List<Commission> getParticipations() {
		return participations;
	}






	public void setParticipations(List<Commission> participations) {
		this.participations = participations;
	}






	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	public LocalDate getInitJob() {
		return initJob;
	}

	public void setInitJob(LocalDate initJob) {
		this.initJob = initJob;
	}

	public int getApprovedLaws() {
		return approvedLaws;
	}

	public void setApprovedLaws(int approvedLaws) {
		this.approvedLaws = approvedLaws;
	}
	
	

}
