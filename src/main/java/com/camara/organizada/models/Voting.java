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
	private VotingStatus votingStatus;
	@OneToOne
	private LegislativeProposal project;
	
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public VotingStatus getVotingStatus() {
		return votingStatus;
	}
	public void setVotingStatus(VotingStatus votingStatus) {
		this.votingStatus = votingStatus;
	}
	public LegislativeProposal getProject() {
		return project;
	}
	public void setProject(LegislativeProposal project) {
		this.project = project;
	}
}
