package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter @Setter
public class LeagueSeason implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4057948904083609171L;

	
	@Id
	@Column
	long 	startingYear;
	
	Calendar startingDate;
	
	Calendar endingDate;
	
	@Transient
	@OneToMany(mappedBy="startingYear")
	List<LeagueDay> days;
	
	@Transient
	@OneToMany(mappedBy="startingYear")
	List<LeagueMatch> matches;

	
	
	
	
	
}
