package com.drunkcode.ateam.api.service.strategy.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.drunkcode.ateam.api.service.dto.FantasyFormation;
import com.drunkcode.ateam.api.service.dto.FantasyPlayer;
import com.drunkcode.ateam.api.service.strategy.ISubstitutionsStrategy;
import com.google.common.collect.ImmutableMap;
/**
 * 
 * @author luca
 * This is an implementation of the strategy intended for defining the substitutes
 * The players without an evaluation need to be substituted by one from the bench with the SAME ROLE.
 * The substitutions are limited in number.
 * The order of the substitutions if defined by the order of the players in the bench list.
 * In this implementation the formation (intended as the system of play) can be changed if a player who must be substituted
 * has no alternatives (a player with the same role) in the bench and the number of changes is still under the max
 */
public class SubstitutionsStrategy implements ISubstitutionsStrategy{
	private static Integer MAX_NUM_OF_KEEPERS=1;
	private static Integer MAX_NUM_OF_DEFENDERS=6;
	private static Integer MAX_NUM_OF_MIDFIELDERS=5;
	private static Integer MAX_NUM_OF_FORWARDS=3;
	
	private static ImmutableMap<String, Integer> maxForRole = ImmutableMap.of(
			"P",MAX_NUM_OF_KEEPERS,
			"D",MAX_NUM_OF_DEFENDERS,
		    "C",MAX_NUM_OF_MIDFIELDERS,
		    "A",MAX_NUM_OF_FORWARDS
		);
		
	
	private Integer MAX_NUM_OF_SUBSTITUTIONS=3;
	private Double GK_DEFAULT_SCORE=6.0;
	private String KEEPER_ROLE="P";
	
	Comparator<FantasyPlayer> playerComparator=(FantasyPlayer p1,FantasyPlayer p2)->{if(p1.getRole().equalsIgnoreCase(KEEPER_ROLE)&&!p2.getRole().equalsIgnoreCase(KEEPER_ROLE)) {
		return -1;
	}else{
		return 0;
	}};
	
	
	@Override
	public FantasyFormation finalizeTeam(FantasyFormation formation) {
		
		List<FantasyPlayer> bench = formation.getBench();
		handleSpecialEvaluationsOfRunners(formation, bench);
		
		//TODO:tenere traccia dei cambi!!!
		int substitutionsCount=0;
		
			
		List<FantasyPlayer> playersWithoutEvaluation = formation.getPlayers().stream().filter(p->p.getEvaluation()==null).collect(Collectors.toList());
		
		playersWithoutEvaluation.sort(playerComparator);
		
		if(!playersWithoutEvaluation.isEmpty()) {
			
			
			handleKeepersWithDefault(playersWithoutEvaluation,bench);
			
				
			//let's check if benched player with an evaluation can substitute players of the SAME role
			for (FantasyPlayer benched : bench) {	
				if(benched.getEvaluation()!=null) {
					Optional<FantasyPlayer> playerWithSameRole =playersWithoutEvaluation.stream().filter(p->p.getRole().equalsIgnoreCase(benched.getRole())).findFirst();
					if(playerWithSameRole.isPresent()) {
						formation.getPlayers().set(formation.getPlayers().indexOf(playerWithSameRole.get()), benched);
						playersWithoutEvaluation.remove(playerWithSameRole.get());
						bench.set(bench.indexOf(benched), null);
						substitutionsCount++;
					}
				}		
				if(substitutionsCount==MAX_NUM_OF_SUBSTITUTIONS)
					break;
			}
			
			//if we still have players to be substituted and we still have available substitutions
			//there's still a chance changing the schema : for each usable benched player we check
			//if his play-zone has room for another player
			if(!playersWithoutEvaluation.isEmpty()&&playersWithoutEvaluation.stream().filter(pwe->!pwe.getRole().equalsIgnoreCase(KEEPER_ROLE)).findAny().isPresent()&&substitutionsCount<MAX_NUM_OF_SUBSTITUTIONS) {
				for(FantasyPlayer benched :bench){
					if(benched!=null&&benched.getEvaluation()!=null) {
						List<FantasyPlayer> playersWithRole = formation.getPlayers().stream().filter(p->p.getRole().equalsIgnoreCase(benched.getRole())).collect(Collectors.toList());
						int numberOfPlayersWithRole = playersWithRole.size();
						if(numberOfPlayersWithRole<maxForRole.get(benched.getRole())) {
							FantasyPlayer substituted = playersWithoutEvaluation.stream().filter(pwe->!pwe.getRole().equalsIgnoreCase(KEEPER_ROLE)).findAny().get();
							formation.getPlayers().set(formation.getPlayers().indexOf(substituted),benched);
							bench.set(bench.indexOf(benched),null);
							playersWithoutEvaluation.remove(substituted);
							substitutionsCount++;
							if(substitutionsCount==MAX_NUM_OF_SUBSTITUTIONS||playersWithoutEvaluation.isEmpty())
								break;
						}
					}
				}
			}
			
			formation.getPlayers().stream().forEach(p->{if(p.getEvaluation()==null) p.setEvaluation(0.0);});
			//if we still have room for substitutions the first player without an evaluation gets a default of 4
			//the remaining ones get 0
			if(!playersWithoutEvaluation.isEmpty()&&substitutionsCount<MAX_NUM_OF_SUBSTITUTIONS) {
				playersWithoutEvaluation.get(0).setEvaluation(4.0);
			}
			formation.setBench(bench);
		}
		
		return formation;
	}


	private void handleSpecialEvaluationsOfRunners(FantasyFormation formation, List<FantasyPlayer> bench) {
		formation.getPlayers().forEach(p->{
			if((!p.getRole().equalsIgnoreCase("P"))&&p.getEvaluation()!=null&&p.getEvaluation()<0)
				p.setEvaluation(null);
		});
		bench.forEach(p->{
			if((!p.getRole().equalsIgnoreCase("P"))&&p.getEvaluation()!=null&&p.getEvaluation()<0)
				p.setEvaluation(null);
		});
	}


	private void handleKeepersWithDefault(List<FantasyPlayer> playersWithoutEvaluation,List<FantasyPlayer> bench) {
		Optional<FantasyPlayer> keeper = playersWithoutEvaluation.stream().filter(p->p.getRole().equalsIgnoreCase(KEEPER_ROLE)).findFirst();
		List<FantasyPlayer> benchedKeepers = bench.stream().filter(p->p.getRole().equalsIgnoreCase(KEEPER_ROLE)).collect(Collectors.toList());
		
		//Keeper gets a default if has received an "S.V." or 6* evaluation 
		//so should not be considered as a player to be substituted
		if (keeper.isPresent()&&keeper.get().getEvaluation()!=null&&keeper.get().getEvaluation()==-1.0) {
			keeper.get().setEvaluation(GK_DEFAULT_SCORE);		
		}
		benchedKeepers.stream().forEach(p->{
			if (p.getEvaluation()!=null&&p.getEvaluation()==-1.0) {
				System.out.println("changing "+p.getName());
				p.setEvaluation(GK_DEFAULT_SCORE);		
			}
		});
		
		
		bench.sort(playerComparator);
	}
	

	
}
