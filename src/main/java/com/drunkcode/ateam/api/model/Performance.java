package com.drunkcode.ateam.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
@Component
public class Performance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966976164776133522L;
	@Id
	@ManyToOne
	@JoinColumn(name="startingYear")
	LeagueSeason 		season;
	@Id
	@ManyToOne
	@JoinColumn(name="dayId")
	LeagueDay 		day;
	@Id
	@ManyToOne
	@JoinColumn(name="matchId")
	LeagueMatch		match;
	@Id
	@ManyToOne
	@JoinColumn(name="player")
	Player		player;
	boolean 	isHomeMatch;
	double		evaluation;
	int			goalsScored;
	int			goalsAllowed;
	int			penaltiesScored;
	int			penaltiesMissed;
	int			penaltiesSaved;
	int			autoGoals;
	int			yellowCards;
	int			redCards;
	int			assists;
	int			assistsFromKick;
	boolean		goalOfVictory;
	boolean 	goalOfTie;
	
	
}
