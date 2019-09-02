package com.drunkcode.ateam.api.service.strategy.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.drunkcode.ateam.api.service.dto.FantasyFormation;
import com.drunkcode.ateam.api.service.dto.FantasyPlayer;
import com.drunkcode.ateam.api.service.strategy.ISubstitutionsStrategy;
/**
 * 
 * @author luca
 * This is an implementation of the strategy intended for defining the substitutes
 * The players without an evaluation need to be substituted by one from the bench with the SAME ROLE.
 * The substitutions are limited in number.
 * The order of the substitutions if defined by the order of the players in the bench list.
 * In this implementation the formation (intended as the system of play) can be changed if a player who must be substituted
 * has no alternatives (a player with the same role) in the bench and the number of changes is still under the max
 * If more than one player has no alternatives than the order of the substitutions will be  defined by role as follows
 * P-D-C-A  
 */
public class SubstitutionsStrategy implements ISubstitutionsStrategy{
	
	private Integer MAX_NUM_OF_DEFENDERS=6;
	private Integer MAX_NUM_OF_MIDFIELDERS=5;
	private Integer MAX_NUM_OF_FORWARDS=3;
	private Integer MAX_NUM_OF_SUBSTITUTIONS=3;
	private Double GK_DEFAULT_SCORE=6.0;
	private String KEEPER_ROLE="P";
	
	
	@Override
	public List<FantasyPlayer> finalizeTeam(FantasyFormation formation) {
		int subbedCount=0;
				
		List<FantasyPlayer> playersWithoutEvaluation = formation.getPlayers().stream().filter(p->p.getEvaluation()==null).collect(Collectors.toList());
		
		List<FantasyPlayer> bench = formation.getBench();
		
		//no module change here
		while (subbedCount < MAX_NUM_OF_SUBSTITUTIONS) {
			for (FantasyPlayer player : playersWithoutEvaluation) {
				//Keeper gets a default if has received any bonus or malus
				if (player.getRole().equals(KEEPER_ROLE) && player.getBonusMalus() != null) {
					player.setEvaluation(GK_DEFAULT_SCORE-player.getBonusMalus());		
					continue;
				}
				FantasyPlayer substituteWithSameRole =bench.stream().filter(p->p.getRole().equalsIgnoreCase(player.getRole())).findFirst().get();
				if(substituteWithSameRole!=null) {
					formation.getPlayers().set(formation.getPlayers().indexOf(player),substituteWithSameRole);
					bench.remove(substituteWithSameRole);
				}
				subbedCount++;			
			}
			
		}
		
		return null;
	}
	

}
