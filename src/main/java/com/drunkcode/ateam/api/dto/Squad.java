package com.drunkcode.ateam.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class Squad {
	
	long fantasyTeamId;
	
	List<SquadMember> members;
	
	List<SquadMember> alternatives;
	
}
