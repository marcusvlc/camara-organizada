package com.camara.organizada.commission;


import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camara.organizada.legistative.LegislativeProposal;
import com.camara.organizada.legistative.PEC;
import com.camara.organizada.legistative.PECRepository;
import com.camara.organizada.legistative.PL;
import com.camara.organizada.legistative.PLP;
import com.camara.organizada.legistative.PLPRepository;
import com.camara.organizada.legistative.PLRepository;
import com.camara.organizada.ruling.RulingParty;
import com.camara.organizada.ruling.RulingPartyRepository;
import com.camara.organizada.user.User;
import com.camara.organizada.user.UserRepository;
import com.camara.organizada.utils.Util;
import com.camara.organizada.voting.Voting;
import com.camara.organizada.voting.VotingDto;
import com.camara.organizada.voting.VotingRepository;

@Service
public class CommissionService {
	
	@Autowired
	private CommissionRepository commissionRep;
	
	@Autowired
	private UserRepository userRep;
	
	private Util utils = new Util();
	
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


	public Commission comissionVoting(String theme, VotingDto votingDto) throws ServletException {
		if(!utils.isValidString(theme) || !utils.isValidString(votingDto.getProposalCode()) || !utils.isValidString(votingDto.getRulingProposalStatus()) || !utils.isValidString(votingDto.getLocal())) {
			throw new ServletException("Campos inválidos ou vazio!");
		}

		Commission isRegistredCommission = commissionRep.findById(theme).orElse(null);
		
		LegislativeProposal isRegistredProposal = findPL(votingDto.getProposalCode());
		
		if(isRegistredCommission == null) {
			throw new ServletException("Não existe nenhuma comissão registrada com esse tema!");
		}
		
		if(isRegistredProposal == null ) {
			throw new ServletException("Não existe nenhuma proposta de lei com esse codigo!");
		}
		
		if(commissionPresent(isRegistredCommission, votingDto.getProposalCode())) {
			throw new ServletException("Esta comissão já votou a esta proposta");
		}
		
		List<RulingParty> rulingParties = rulingPartyRepository.findAll();
		
		
		String votingStatus = isRegistredCommission.passLaw(isRegistredProposal, votingDto.getRulingProposalStatus(), rulingParties);		
		
		Voting newVoting = createVoting(votingDto, votingStatus);
		
		Voting savedVote = votingRepository.save(newVoting);
		isRegistredCommission.setVotingProposals(savedVote);
		
		
		String currentStatus = isRegistredProposal.getCurrentSituation().get(isRegistredProposal.getCurrentSituation().size() - 1);
		
		String currentLocal = getLocalStatus(currentStatus);
		
		currentStatus = votingStatus + " (" + currentLocal + ")";
		
		isRegistredProposal.getCurrentSituation().set(isRegistredProposal.getCurrentSituation().size() - 1, currentStatus);
		
		isRegistredProposal.getCurrentSituation().add("EM VOTAÇÃO (" + votingDto.getLocal() + ")");
				
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


	private Voting createVoting(VotingDto votingDto, String votingStatus) {
		Voting newVoting = new Voting();
		newVoting.setLocal(votingDto.getLocal());
		newVoting.setVotingStatus(votingStatus);
		newVoting.setProposalCode(votingDto.getProposalCode());
		newVoting.setRulingProposalStatus(votingDto.getRulingProposalStatus());
		
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
		for (Iterator<Voting> iterator = commission.getVotingProposals().iterator(); iterator.hasNext();) {
			Voting vote= (Voting) iterator.next();
			if(vote.equals(proposalCode)) {
				return true;
			}
		}
		return false;
	}


}
