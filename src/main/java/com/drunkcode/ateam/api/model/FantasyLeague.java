package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class FantasyLeague {
	
	private String name;
	
	private FantasyLeagueOptions options;
	
	private FantasyRuleSet ruleset;
	
	
	
}
