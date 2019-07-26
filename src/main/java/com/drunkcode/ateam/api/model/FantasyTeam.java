package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FantasyTeam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5499273645532044956L;
	
	@Id
	long 		fantasyTeamId;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="userId")
	User		user;
	String		name;
	
	List<FantasyContract> contracts;
	
	Short remainingCredits;
	


}
