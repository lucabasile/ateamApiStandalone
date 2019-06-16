package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

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
//	@GeneratedValue
//	long 	matchId;
	
	
	
	
	@EmbeddedId
	LeagueMatchId leagueMatchId;
	
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Match_Player", 
        //joinColumns = { @JoinColumn(name = "leagueMatchId") },
			joinColumns={
					@JoinColumn(name="homeTeam", insertable=false, updatable=false),
					@JoinColumn(name = "awayTeam", insertable=false, updatable=false),
					@JoinColumn(name="day", insertable=false, updatable=false),
					@JoinColumn(name = "year", insertable=false, updatable=false)
			},
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
