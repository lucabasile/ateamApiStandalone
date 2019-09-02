package com.drunkcode.ateam.api.service.strategy;

import java.util.List;

import com.drunkcode.ateam.api.service.dto.MidfieldModifier;

public interface IMidfieldModifierCalculationStrategy {
	public MidfieldModifier calculateModifier(List<Double> homeTeamMidfielders,List<Double> awayTeamMidfielders);

}
