package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Match_Player", 
        joinColumns = { @JoinColumn(name = "matchId") }, 
        inverseJoinColumns = { @JoinColumn(name = "playerId") }
    )
	List<Player> players;
	
	
	Calendar 	date;
	Boolean 	completed;
	Boolean		delayedMatch;
	Boolean		suspended;
	Calendar 	resumeDate;
	Integer		homeGoals;
	Integer		awayGoals;
	

}
