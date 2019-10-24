package com.camara.organizada.voting;

public class VotingDto {

	private String proposalCode;
	private String rulingProposalStatus;
	private String local;
	
	
	public String getProposalCode() {
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	
}
