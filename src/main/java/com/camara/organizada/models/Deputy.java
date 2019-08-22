package com.camara.organizada.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Deputy {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Date initJob;
	private int approvedLaws;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	public Deputy() {
		this.approvedLaws = 0;
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





	public Date getInitJob() {
		return initJob;
	}

	public void setInitJob(Date initJob) {
		this.initJob = initJob;
	}

	public int getApprovedLaws() {
		return approvedLaws;
	}

	public void setApprovedLaws(int approvedLaws) {
		this.approvedLaws = approvedLaws;
	}
	
	

}
