package com.drunkcode.ateam.api.service.dto;

import lombok.Data;

@Data
public class FantasyPlayer {
	
	public FantasyPlayer() {
		
	}
	
	public FantasyPlayer(long id,String role,String name,Double evaluation,Double bonusMalus) {
		this.id=id;
		this.role=role;
		this.name=name;
		this.evaluation=evaluation;
		this.bonusMalus=bonusMalus;
	}
	
	long id;
	
	String role;
	
	String name;
	
	Double evaluation;
	
	Double bonusMalus;
	
	@Override 
	public String toString() {
		return this.id+" "+this.name+" "+this.role+" "+this.getEvaluation()+" "+this.getBonusMalus();
	}
	
}
