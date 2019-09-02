package com.drunkcode.ateam.api.service.dto;

import lombok.Data;

@Data
public class FantasyPlayer {
	
	long id;
	
	String role;
	
	String name;
	
	Double evaluation;
	
	Double bonusMalus;
	
}
