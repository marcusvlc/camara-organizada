package com.camara.organizada.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Commission {
	
	private String initials;
	@Id
	private String theme;
	
	@ManyToMany
	private List<Deputy> participants;
	
	@ManyToMany
	private List<Voting> votingProposals;
	
	public List<Voting> getVotingProposals() {
		return votingProposals;
	}

	public void setVotingProposals(List<Voting> votingProposals) {
		this.votingProposals = votingProposals;
	}

	public Commission() {
		this.participants = new ArrayList<Deputy>();
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<Deputy> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Deputy> participants) {
		this.participants = participants;
	}
	
	

}
