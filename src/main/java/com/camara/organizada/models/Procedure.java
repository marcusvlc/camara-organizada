package com.camara.organizada.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Procedure {

	@OneToMany
	private List<Voting> votingSessions;
	
	private ProcedureStatus procedureStatus;

	public List<Voting> getVotingSessions() {
		return votingSessions;
	}

	public void setVotingSessions(List<Voting> votingSessions) {
		this.votingSessions = votingSessions;
	}

	public ProcedureStatus getProcedureStatus() {
		return procedureStatus;
	}

	public void setProcedureStatus(ProcedureStatus procedureStatus) {
		this.procedureStatus = procedureStatus;
	}
}
