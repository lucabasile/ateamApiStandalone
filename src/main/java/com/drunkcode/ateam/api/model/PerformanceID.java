package com.drunkcode.ateam.api.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class PerformanceID implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7589745972495863010L;
	
	@ManyToOne
	@JoinColumn(name="startingYear")
	LeagueSeason 		season;
	
	@ManyToOne
	@JoinColumn(name="dayId")
	LeagueDay 		day;
	
	@ManyToOne
	@JoinColumn(name="matchId")
	LeagueMatch		match;
	
	@ManyToOne
	@JoinColumn(name="player")
	Player		player;
}
