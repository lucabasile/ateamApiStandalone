package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class FantasyLeague {
	
	@Id
	long 		fantasyLeagueId;
	
	private String name;
	
	Boolean enableExchanges;
	
	Boolean usesKeeperModifier;
	
	Boolean usesDefenseModifier;
	
	Boolean usesMidfieldModifier;
	
	Boolean usesAttackModifier;
	
	Integer maxCredits;
	
}
