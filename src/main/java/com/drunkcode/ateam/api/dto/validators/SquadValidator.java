package com.drunkcode.ateam.api.dto.validators;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.commons.Constants;
import com.drunkcode.ateam.api.dto.Squad;
import com.drunkcode.ateam.api.dto.SquadMember;
import com.drunkcode.ateam.api.dto.validators.annotations.SquadConstraint;
import com.drunkcode.ateam.api.model.FantasyContract;
import com.drunkcode.ateam.api.model.FantasyTeam;
import com.drunkcode.ateam.api.repository.FantasyTeamRepository;
import com.google.common.collect.Range;

import lombok.Setter;

@Setter
@Component
public class SquadValidator implements ConstraintValidator<SquadConstraint,Squad >{
	
	Logger logger = LoggerFactory.getLogger(SquadValidator.class);
	
	@Autowired
	FantasyTeamRepository fantasyTeamRepo;

	@Override
	public boolean isValid(Squad squad, ConstraintValidatorContext context) {

		boolean isValid = checkMembersNumber(squad)
				&&checkAlternativesNumber(squad)&&	
				checkMembersAndAlternativesPeople(squad);
		
		
	
		return isValid;
	}

	private boolean checkMembersAndAlternativesPeople(Squad squad) {
		boolean isValid=false;
		Optional<FantasyTeam> team = fantasyTeamRepo.findById(squad.getFantasyTeamId());
		if(team.isPresent()) {
			List<FantasyContract> contracts = team.get().getContracts();
			boolean membersOK = false;
			for(SquadMember member :squad.getMembers()){
				membersOK = contracts.stream().filter(c->c.getPlayer().getPlayerId()==member.getId()).findAny().isPresent();
				if(!membersOK) {
					logger.error("Team "+team.get().getName()+" has no contract for player with id "+member.getId());
				}
				break;
			}
			boolean alternativesOK = false;
			if(membersOK) {
				for(SquadMember substitute :squad.getAlternatives()){
					alternativesOK = contracts.stream().filter(c->c.getPlayer().getPlayerId()==substitute.getId()).findAny().isPresent();
					if(!alternativesOK) {
						logger.error("Team "+team.get().getName()+" has no contract for player with id "+substitute.getId());
					}
					break;
				}
			}
			
			isValid=membersOK&&alternativesOK;
		}else {
			logger.error("Team "+squad.getFantasyTeamId()+" not Found");
		}
		return isValid;
	}

	private boolean checkMembersNumber(Squad squad) {
		Range<Integer> defendersRange = Range.closed(0, 6);
		Range<Integer> midfieldersRange = Range.closed(0, 5);
		Range<Integer> forwardsRange = Range.closed(0, 3);
		
		boolean isOk=false;
		if(squad.getMembers().size()==Constants.MAX_NUMBER_OF_SQUAD_MEMBERS) {
			int numberOfKeepers = squad.getMembers().stream().filter(m->m.getRole().equalsIgnoreCase(Constants.KEEPER_ROLE)).collect(Collectors.toList()).size();
			int numberOfDefenders = squad.getMembers().stream().filter(m->m.getRole().equalsIgnoreCase(Constants.DEFENDER_ROLE)).collect(Collectors.toList()).size();
			int numberOfMidfielders = squad.getMembers().stream().filter(m->m.getRole().equalsIgnoreCase(Constants.MIDFIELDER_ROLE)).collect(Collectors.toList()).size();
			int numberOfForwards = squad.getMembers().stream().filter(m->m.getRole().equalsIgnoreCase(Constants.FORWARD_ROLE)).collect(Collectors.toList()).size();
			
			isOk=numberOfKeepers==1&&
					defendersRange.contains(numberOfDefenders)&&
					midfieldersRange.contains(numberOfMidfielders)&&
					forwardsRange.contains(numberOfForwards);
		}
		
		return isOk;
	}
	
	private boolean checkAlternativesNumber(Squad squad) {
		return squad.getAlternatives().size()<=13;
	}
		

}
