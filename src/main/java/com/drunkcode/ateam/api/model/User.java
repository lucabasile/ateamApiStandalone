package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class User implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5673692657658280616L;
	@Id
	long 		userId;
	String		name;
	String		surname;
	Calendar 	birthDate;
	String		address;
	String		state;
	String		region;
	String		phone;
	String		gender;
	long		favouriteTeamId;
	Calendar	lastLogIn;
	
	
}
