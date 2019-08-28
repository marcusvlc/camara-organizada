package com.camara.organizada.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Voting {

	@Id
	@GeneratedValue
	private Long id;
	
	private String local;
	private String votingStatus;
	private String rulingProposalStatus;
	private String proposalCode;
	
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getVotingStatus() {
		return votingStatus;
	}
	public void setVotingStatus(String votingStatus) {
		this.votingStatus = votingStatus;
	}
	public String getProject() {
		return proposalCode;
	}
	public void setProject(String project) {
		this.proposalCode = proposalCode;
	}
	public String getRulingProposalStatus() {
		return rulingProposalStatus;
	}
	public void setRulingProposalStatus(String rulingProposalStatus) {
		this.rulingProposalStatus = rulingProposalStatus;
	}
}
