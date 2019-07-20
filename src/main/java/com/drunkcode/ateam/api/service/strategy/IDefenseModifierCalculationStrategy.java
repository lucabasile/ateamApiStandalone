package com.drunkcode.ateam.api.service.strategy;

import java.util.ArrayList;

import com.drunkcode.ateam.api.service.dto.DefenseModifier;

public interface IDefenseModifierCalculationStrategy {
	public DefenseModifier calculateModifier(ArrayList<Double> evaluations);	
}
