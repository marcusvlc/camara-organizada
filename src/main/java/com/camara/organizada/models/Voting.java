package com.camara.organizada.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Voting {

	private String local;
	private VotingStatus votingStatus;
	private RulingStatus rulingStatus;
	@ManyToMany
	private List<Commission> votingCommissions;
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
	public RulingStatus getRulingStatus() {
		return rulingStatus;
	}
	public void setRulingStatus(RulingStatus rulingStatus) {
		this.rulingStatus = rulingStatus;
	}
	public List<Commission> getVotingCommissions() {
		return votingCommissions;
	}
	public void setVotingCommissions(List<Commission> votingCommissions) {
		this.votingCommissions = votingCommissions;
	}
	public LegislativeProposal getProject() {
		return project;
	}
	public void setProject(LegislativeProposal project) {
		this.project = project;
	}
}
