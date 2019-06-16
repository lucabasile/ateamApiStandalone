package com.drunkcode.ateam.api.model;

import java.io.Serializable;

import javax.persistence.*;

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
	@JoinColumns({
		@JoinColumn(name="homeTeam" , insertable=false, updatable=false),
			@JoinColumn(name = "awayTeam", insertable=false, updatable=false),
			@JoinColumn(name="day", insertable=false, updatable=false),
			@JoinColumn(name = "year", insertable=false, updatable=false)
	})
	LeagueMatch		match;
	
	@ManyToOne
	@JoinColumn(name="player")
	Player		player;
}
