package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5015662718139295889L;

	@Id
	@GeneratedValue
	long 		teamId;
	
	@Column(unique=true)
	String 		name;
	int			points;
	int			goalsFor;
	int			goalsAgainst;
	int			pointsAtHome;
	int			pointsAway;
	int 		penalties;
	int			scoredPenalties;
	int			missedPenalties;
	int			homeGoalsFor;
	int			homeGoalsAgainst;
	int			awayGoalsFor;
	int			awayGoalsAgainst;
	int			handicap;
	
	@Transient
	@OneToMany(mappedBy="teamId")
	List<Player> players;
	
}
