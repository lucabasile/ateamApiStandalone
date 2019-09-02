package com.drunkcode.ateam.api.service.strategy.impl;

import java.util.List;

import com.drunkcode.ateam.api.service.dto.MidfieldModifier;
import com.drunkcode.ateam.api.service.strategy.IMidfieldModifierCalculationStrategy;

public class MidfieldModifierCalculationstrategy implements IMidfieldModifierCalculationStrategy{

	@Override
	public MidfieldModifier calculateModifier(List<Double> homeTeamMidfielders, List<Double> awayTeamMidfielders) {
		
		//Adding all homeTeamMidfielders evaluations
		Double homeTotal=homeTeamMidfielders.stream().reduce(0.0,(a,b)->a+b);
		//Adding all awayTeamMidfielders evaluations
		Double awayTotal=awayTeamMidfielders.stream().reduce(0.0,(a,b)->a+b);
		//Splitting in half
		Double rawModifier=(homeTotal-awayTotal)/2.0;
		
		MidfieldModifier modifier= new MidfieldModifier();
		
		//Defining the modifier : the part exceeding the step is erased
		modifier.setHomeTeamModifier(rawModifier-(rawModifier%0.5));
		modifier.setAwayTeamModifier(-modifier.getHomeTeamModifier());
		
		return modifier;
	}

}
