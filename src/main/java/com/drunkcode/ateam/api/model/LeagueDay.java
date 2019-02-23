package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Component
public class LeagueDay implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7142758110818795283L;

	@Id
	@GeneratedValue
	long dayId;
	
	int dayIndex;
	
	
	@ManyToOne(targetEntity=LeagueSeason.class)
	@JoinColumn(name="startingYear")
	LeagueSeason season;
	
	
	Boolean completed;
	
	@Transient
	@OneToMany(mappedBy="dayId")
	List<LeagueMatch> matches;
	
}
