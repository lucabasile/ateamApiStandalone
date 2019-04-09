package com.drunkcode.ateam.api.VO;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlayerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5965860864253881799L;
	
	private		long		playerId;
	private		String 		name;
	private		String 		role;
	private		Calendar 	birthDate;
	private		String 		teamName;
	private		String 		teamId;
	private 	String		previousTeams;
	private 	int			daysOut;

}
