package com.camara.organizada.services;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.camara.organizada.controllers.CommissionDto;
import com.camara.organizada.models.Commission;
import com.camara.organizada.models.LegislativeProposal;
import com.camara.organizada.models.PEC;
import com.camara.organizada.models.PL;
import com.camara.organizada.models.PLP;
import com.camara.organizada.models.RulingParty;
import com.camara.organizada.models.User;
import com.camara.organizada.models.Voting;
import com.camara.organizada.repositories.CommissionRepository;
import com.camara.organizada.repositories.PECRepository;
import com.camara.organizada.repositories.PLPRepository;
import com.camara.organizada.repositories.PLRepository;
import com.camara.organizada.repositories.RulingPartyRepository;
import com.camara.organizada.repositories.UserRepository;
import com.camara.organizada.repositories.VotingRepository;
import com.camara.organizada.utils.Util;

@Service
public class CommissionService {
	
	@Autowired
	private CommissionRepository commissionRep;
	
	@Autowired
	private UserRepository userRep;
	
	private Util utils = new Util();
	@Autowired
	private PLRepository proposalRep;
	
	@Autowired
	private VotingRepository votingRepository; 
	@Autowired
	private RulingPartyRepository rulingPartyRepository;
	@Autowired
	private PLRepository PLRep;
	@Autowired
	private PLPRepository PLPRep;
	@Autowired
	private PECRepository PECRep; 
	
	
	public Commission registerCommission(CommissionDto payload) throws ServletException {
		
		Commission commission = convertToEntity(payload);
		
		Commission savedCommission = commissionRep.save(commission);
		
		return savedCommission;
	}
	
	private Commission convertToEntity(CommissionDto payload) throws ServletException {
		if(!utils.isValidString(payload.getInitials()) || !utils.isValidString(payload.getDnis()) || !utils.isValidString(payload.getTheme())) {
			throw new ServletException("Campos inválidos ou vazio!");
		}
		
		Commission isRegistredCommission = commissionRep.findById(payload.getTheme()).orElse(null);
		
		if(isRegistredCommission != null) {
			throw new ServletException("Já existe uma comissão registrada com esse tema!");
		}
		
		String[] formatedDnis = payload.getDnis().split(",");
		
		Commission commission = new Commission();
		commission.setInitials(payload.getInitials());
		commission.setTheme(payload.getTheme());
		
		for(String dni: formatedDnis) {
			if(!utils.isValidDNI(dni)) {
				throw new ServletException("O DNI " + dni + "é inválido!" );
			} else {
				User user = userRep.findById(dni).orElse(null);
				
				if(user == null) {
					throw new ServletException("Não foi encontrado um usuário com o dni: " + dni);
				} else {
					if(user.getDeputy() == null) {
						throw new ServletException("O usuário com o dni: " + dni + "não é um deputado!");
					} else {
						commission.getParticipants().add(user.getDeputy());
					}
				}
			}
		}
		
		return commission;
	}


	public Commission comissionVoting(String theme, String proposalCode, String rulingProposalStatus, String local) throws ServletException {
		if(!utils.isValidString(theme) || !utils.isValidString(proposalCode) || !utils.isValidString(rulingProposalStatus) || !utils.isValidString(local)) {
			throw new ServletException("Campos inválidos ou vazio!");
		}

		Commission isRegistredCommission = commissionRep.findById(theme).orElse(null);
		
		LegislativeProposal isRegistredProposal = findPL(proposalCode);
		
		if(isRegistredCommission == null) {
			throw new ServletException("Não existe nenhuma comissão registrada com esse tema!");
		}
		
		if(isRegistredProposal == null ) {
			throw new ServletException("Não existe nenhuma proposta de lei com esse codigo!");
		}
		
		if(commissionPresent(isRegistredCommission, proposalCode)) {
			throw new ServletException("Esta comissão já votou a esta proposta");
		}
		
		List<RulingParty> rulingParties = rulingPartyRepository.findAll();
		
		
		String votingStatus = isRegistredCommission.passLaw(isRegistredProposal, rulingProposalStatus, rulingParties);		
		
		Voting newVoting = createVoting(proposalCode, rulingProposalStatus, local, votingStatus);
		
		Voting savedVote = votingRepository.save(newVoting);
		isRegistredCommission.setVotingProposals(savedVote);
		
		
		String currentStatus = isRegistredProposal.getCurrentSituation().get(isRegistredProposal.getCurrentSituation().size() - 1);
		
		String currentLocal = getLocalStatus(currentStatus);
		
		currentStatus = votingStatus + " (" + currentLocal + ")";
		
		isRegistredProposal.getCurrentSituation().set(isRegistredProposal.getCurrentSituation().size() - 1, currentStatus);
		
		isRegistredProposal.getCurrentSituation().add("EM VOTAÇÃO (" + local + ")");
				
		updateProposal(isRegistredProposal);
		
		Commission updatedCommission = commissionRep.save(isRegistredCommission);

		return updatedCommission;
	}


	private String getLocalStatus(String currentStatus) {
		String localName = "";
		boolean startPickName = false;
		for(int i = 0; i < currentStatus.length(); i++) {
			if(currentStatus.charAt(i) == '(') {
				startPickName = true;
			}
			
			if(startPickName) {
				for(int j = i+1; j < currentStatus.length(); j++) {
					
					if(currentStatus.charAt(j) == ')') {
						return localName;
					}
					
					localName += currentStatus.charAt(j);
				}
			}
		}
		
		return localName;
	}


	private void updateProposal(LegislativeProposal isRegistredProposal) {
		 if(PLRep.existsById(isRegistredProposal.getCode())) {
			 PL plUpdate = (PL) isRegistredProposal;
			 PLRep.save(plUpdate);
			 
		 } else if(PLPRep.existsById(isRegistredProposal.getCode())) {
			 PLP plpUpdate = (PLP) isRegistredProposal;
			 PLPRep.save(plpUpdate);
		 } else if(PECRep.existsById(isRegistredProposal.getCode())) {
			 PEC pecUpdate = (PEC) isRegistredProposal;
			 PECRep.save(pecUpdate);
		 }
		 
		
	}


	private Voting createVoting(String proposalCode, String rulingProposalStatus, String local, String votingStatus) {
		Voting newVoting = new Voting();
		newVoting.setLocal(local);
		newVoting.setVotingStatus(votingStatus);
		newVoting.setProposalCode(proposalCode);
		newVoting.setRulingProposalStatus(rulingProposalStatus);
		return newVoting;
	}


	public LegislativeProposal findPL(String proposalCode) {
		 LegislativeProposal isRegistredProposal = null;
		 
		 if(PLRep.existsById(proposalCode)) {
			 isRegistredProposal = PLRep.findById(proposalCode).orElse(null);
		 } else if(PLPRep.existsById(proposalCode)) {
			 isRegistredProposal = PLPRep.findById(proposalCode).orElse(null);
		 } else if(PECRep.existsById(proposalCode)) {
			 isRegistredProposal = PECRep.findById(proposalCode).orElse(null);
		 }
		 
		 return isRegistredProposal;
		 
	}

	public boolean commissionPresent(Commission commission, String proposalCode){
		for (Iterator iterator = commission.getVotingProposals().iterator(); iterator.hasNext();) {
			Voting vote= (Voting) iterator.next();
			if(vote.equals(proposalCode)) {
				return true;
			}
		}
		return false;
	}


}
