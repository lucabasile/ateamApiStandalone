package com.drunkcode.ateam.api.service.strategy;

import java.util.List;

import com.drunkcode.ateam.api.service.dto.FantasyFormation;
import com.drunkcode.ateam.api.service.dto.FantasyPlayer;

public interface ISubstitutionsStrategy {
	public List<FantasyPlayer> finalizeTeam(FantasyFormation formation);
}
