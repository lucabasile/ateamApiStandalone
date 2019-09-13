package com.drunkcode.ateam.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
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
	
	@Transient
	@OneToMany(mappedBy="fantasyContractId")
	List<FantasyContract> contracts;
	
	Short remainingCredits;
	


}
