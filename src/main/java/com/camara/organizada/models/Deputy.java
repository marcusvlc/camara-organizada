package com.camara.organizada.models;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Deputy extends User {
	
	private Date initJob;
	private int approvedLaws;
	
	public Deputy() {
		this.approvedLaws = 0;
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
