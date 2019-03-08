package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Component
@EntityListeners(AuditingEntityListener.class)
public class Player implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3932096378227375647L;

	@Id
	long		playerId;
	
	@Column
	String 		name;
	
	String 		role;
	Calendar 	birthDate;
	@ManyToOne(targetEntity=Team.class)
	@JoinColumn(name="teamId")
	Team		team;
	String		previousTeams;
	int			daysOut;
	private Date updatedAt;
	private Date createdAt;
	@JsonIgnore
	@ManyToMany(mappedBy="players")
//	@JoinColumn(name="teamId_p")
	List<LeagueMatch> matches;
	
}
