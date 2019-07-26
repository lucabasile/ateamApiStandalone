package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class FantasyContract {

	FantasySeason StartingFantasySeason;
	
	FantasySeason EndingFantasySeason;
	
	Short paidCredits;
	
	Player player;
	
	FantasyTeam team;
	
	
	
}
