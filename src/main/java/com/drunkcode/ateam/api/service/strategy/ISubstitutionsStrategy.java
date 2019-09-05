package com.drunkcode.ateam.api.service.strategy;

import com.drunkcode.ateam.api.service.dto.FantasyFormation;

public interface ISubstitutionsStrategy {
	public FantasyFormation finalizeTeam(FantasyFormation formation);
}
