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
	
	public Commission() {
		this.participants = new ArrayList<Deputy>();
		this.votingProposals = new ArrayList<Voting>();
	}
	
	public List<Voting> getVotingProposals() {
		return votingProposals;
	}

	public void setVotingProposals(Voting votingProposals) {
		this.votingProposals.add(votingProposals);
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
	
	public String passLaw(LegislativeProposal proposal, String rulingProposalStatus, List<RulingParty> rulingParties ) {
		String free = "LIVRE";
		int inFavorTotalVotes;
		String votingStatus;
		
		
		if (rulingProposalStatus.equals(free)) {
			
			inFavorTotalVotes = voteFreeProposal(proposal);
		}
		else {
			
			inFavorTotalVotes = voteAccording2RulingPosition(rulingProposalStatus, rulingParties);
		}
		
		
		votingStatus = resolvingStatus(inFavorTotalVotes);
		
		return votingStatus;
	}

	private String resolvingStatus(int inFavorTotalVotes) {
		String votingStatus;
		
		if (inFavorTotalVotes >= (this.getParticipants().size()/2) +1) {
			votingStatus = "APROVAR";
		} else {
			votingStatus = "REJEITAR";
		}
		return votingStatus;
	}

	private int voteAccording2RulingPosition(String rulingProposalStatus, List<RulingParty> rulingParties) {
		int votesSum = 0;
		for (Iterator iterator = participants.iterator(); iterator.hasNext();) {
			Deputy deputy = (Deputy) iterator.next();
			//Ajustar govermentVote
			votesSum += deputy.govermentVote(rulingProposalStatus, rulingParties);
		}
		return votesSum;
	}

	private int voteFreeProposal(LegislativeProposal proposal) {
		int votesSum = 0;
		String proposalInterests = proposal.getInterests();
		for (Iterator iterator = this.participants.iterator(); iterator.hasNext();) {
			Deputy deputy = (Deputy) iterator.next();

			votesSum += deputy.vote(proposalInterests);
		}
		return votesSum;
		
	}

}
