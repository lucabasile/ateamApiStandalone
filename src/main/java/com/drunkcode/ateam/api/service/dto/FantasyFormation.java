package com.drunkcode.ateam.api.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class FantasyFormation {
	List<FantasyPlayer> players;
	
	List<FantasyPlayer> bench;
}
