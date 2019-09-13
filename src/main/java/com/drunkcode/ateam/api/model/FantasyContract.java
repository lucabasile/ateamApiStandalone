package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class FantasyContract {
	
	@Id
	long 		fantasyContractId;
	
	@ManyToOne(targetEntity=	FantasySeason.class)
	@JoinColumn(name="startingYear",insertable=false,updatable=false)
	FantasySeason StartingFantasySeason;
	
	@ManyToOne(targetEntity=	FantasySeason.class)
	@JoinColumn(name="startingYear",insertable=false,updatable=false)
	FantasySeason EndingFantasySeason;
	
	Short paidCredits;
	
	@ManyToOne(targetEntity=Player.class)
	@JoinColumn(name="playerId")
	Player player;
	
	@ManyToOne(targetEntity=FantasyTeam.class)
	@JoinColumn(name="fantasyTeamId")
	FantasyTeam team;
	
	@Enumerated(EnumType.STRING)
	ContractType type;
	
	
}
