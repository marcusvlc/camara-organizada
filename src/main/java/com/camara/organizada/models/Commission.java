package com.camara.organizada.models;

import java.util.ArrayList;
import java.util.Iterator;
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

	public void setVotingProposals(Voting votingProposals) {
		this.votingProposals.add(votingProposals);
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
	
	public String passLaw(LegislativeProposal proposal, String rulingProposalStatus) {
		String ruling = "GOVERNISTA";
		String counterPart= "OPOSICAO";
		String free = "LIVRE";
		int inFavor;
		String votingStatus;
		if (proposal.equals(free)) {
			inFavor = partitionVotes(proposal);
		}
		else {
			inFavor = partitionRulingPosition(rulingProposalStatus);
		}
		
		if (inFavor >= (this.getParticipants().size()/2) +1) {
			votingStatus = "APROVAR";
		} else {
			votingStatus = "REJEITAR";
		}
		return votingStatus;
	}

	private int partitionRulingPosition(String rulingProposalStatus) {
		int votesSum = 0;
		for (Iterator iterator = participants.iterator(); iterator.hasNext();) {
			Deputy deputy = (Deputy) iterator.next();
			//Ajustar govermentVote
			votesSum += deputy.govermentVote(rulingProposalStatus);
		}
		return votesSum;
	}

	private int partitionVotes(LegislativeProposal proposal) {
		int votesSum = 0;
		for (Iterator iterator = participants.iterator(); iterator.hasNext();) {
			Deputy deputy = (Deputy) iterator.next();
			votesSum += deputy.vote(proposal.getInterests());
		}
		return votesSum;
		
	}

}
