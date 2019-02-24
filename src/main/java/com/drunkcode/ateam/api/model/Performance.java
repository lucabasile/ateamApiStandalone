package com.drunkcode.ateam.api.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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
	
	@EmbeddedId
	PerformanceID performanceID;
	
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
