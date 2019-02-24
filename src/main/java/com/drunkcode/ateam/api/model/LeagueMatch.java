package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter @Setter
public class LeagueMatch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9021576164146309388L;
	@Id
	@GeneratedValue
	long 	matchId;
	
	
	
	
	@ManyToOne(targetEntity=Team.class)
	@JoinColumn(name="homeTeam")
	Team	homeTeam;
	
	@ManyToOne(targetEntity=Team.class)
	@JoinColumn(name="awayTeam")
	Team 	awayTeam;
	
	@ManyToOne(targetEntity=LeagueDay.class)
	@JoinColumn(name="dayId")
	LeagueDay 	day;
	
	@ManyToOne(targetEntity=LeagueSeason.class)
	@JoinColumn(name="startingYear")
	LeagueSeason	season;
	
	
	Calendar 	date;
	Boolean 	completed;
	Boolean		delayedMatch;
	Boolean		suspended;
	Calendar 	resumeDate;
	Integer		homeGoals;
	Integer		awayGoals;
	

}
