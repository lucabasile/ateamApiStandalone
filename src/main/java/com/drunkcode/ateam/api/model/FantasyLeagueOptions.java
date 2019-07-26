package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class FantasyLeagueOptions {

	Boolean enableExchanges;
	
	Boolean usesKeeperModifier;
	
	Boolean usesDefenseModifier;
	
	Boolean usesMidfieldModifier;
	
	Boolean usesAttackModifier;
}
