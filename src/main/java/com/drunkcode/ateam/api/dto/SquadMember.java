package com.drunkcode.ateam.api.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SquadMember {
	
	String name;
	
	String role;
	
	Integer captainOrder;
	
	Integer penaltyOrder;
	
	@NotNull
	Long id;
}
