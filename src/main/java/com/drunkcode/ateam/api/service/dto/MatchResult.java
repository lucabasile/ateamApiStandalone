package com.drunkcode.ateam.api.service.dto;

import lombok.Data;

@Data
public class MatchResult {

	KeeperModifier keeperModifier;
	
	DefenseModifier defenseModifier;
	
	MidfieldModifier midfieldModifier;
	
	AttackModifier attackModifier;
}
