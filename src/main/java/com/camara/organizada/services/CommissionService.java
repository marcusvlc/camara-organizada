package com.camara.organizada.services;


import java.util.Iterator;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.camara.organizada.models.Commission;
import com.camara.organizada.models.LegislativeProposal;
import com.camara.organizada.models.User;
import com.camara.organizada.models.Voting;
import com.camara.organizada.repositories.CommissionRepository;
import com.camara.organizada.repositories.PLRepository;
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
	
	
	public Commission registerCommission(String initials, String dnis, String theme) throws ServletException {
		
		if(!utils.isValidString(initials) || !utils.isValidString(dnis) || !utils.isValidString(theme)) {
			throw new ServletException("Campos inválidos ou vazio!");
		}
		
		Commission isRegistredCommission = commissionRep.findById(theme).orElse(null);
		
		if(isRegistredCommission != null) {
			throw new ServletException("Já existe uma comissão registrada com esse tema!");
		}
		
		String[] formatedDnis = dnis.split(",");
		
		Commission commission = new Commission();
		commission.setInitials(initials);
		commission.setTheme(theme);
		
		
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
		
		Commission savedCommission = commissionRep.save(commission);
		
		return savedCommission;
	}


	public Commission comissionVoting(String theme, String proposalCode, String rulingProposalStatus, String local) throws ServletException {
		if(!utils.isValidString(theme) || !utils.isValidString(proposalCode) || !utils.isValidString(rulingProposalStatus) || !utils.isValidString(local)) {
			throw new ServletException("Campos inválidos ou vazio!");
		}

		Commission isRegistredCommission = commissionRep.findById(theme).orElse(null);
		
		LegislativeProposal isRegistredProposal= proposalRep.findById(theme).orElse(null);
		
		if(isRegistredCommission == null) {
			throw new ServletException("Não existe nenhuma comissão registrada com esse tema!");
		}
		
		if(commissionPresent(isRegistredCommission, proposalCode)) {
			throw new ServletException("Esta comissão já votou a esta proposta");
		}
		
		String votingStatus = isRegistredCommission.passLaw(isRegistredProposal, rulingProposalStatus);
	
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		Voting newVoting = new Voting();
		newVoting.setLocal(local);
		newVoting.setVotingStatus(votingStatus);
		newVoting.setProposalCode(proposalCode);
		newVoting.setRulingProposalStatus(rulingProposalStatus);
		
		Voting savedVote = votingRepository.save(newVoting);
		isRegistredCommission.setVotingProposals(savedVote);
		
		Commission updatedCommission = commissionRep.save(isRegistredCommission);

		return updatedCommission;
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
