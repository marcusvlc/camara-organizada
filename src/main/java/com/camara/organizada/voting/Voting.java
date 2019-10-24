package com.camara.organizada.voting;

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
	public String getproposalCode() {
		return proposalCode;
	}
	public void setProposalCode(String proposalCode) {
		this.proposalCode = proposalCode;
	}
	public String getRulingProposalStatus() {
		return rulingProposalStatus;
	}
	public void setRulingProposalStatus(String rulingProposalStatus) {
		this.rulingProposalStatus = rulingProposalStatus;
	}
}
