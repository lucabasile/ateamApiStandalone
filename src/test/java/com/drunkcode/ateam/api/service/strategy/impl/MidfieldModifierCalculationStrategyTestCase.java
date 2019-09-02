package com.drunkcode.ateam.api.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.drunkcode.ateam.api.service.dto.MidfieldModifier;

class MidfieldModifierCalculationStrategyTestCase {

	
	MidfieldModifierCalculationstrategy strategy = new MidfieldModifierCalculationstrategy();
		
	@DisplayName("Check midfield modifier calculation")
	@ParameterizedTest(name = "{index} => d1={0}, d2={1},expected={2}")
	@CsvFileSource (resources="/midModTest.csv")
	void testCalculateModifier(String homeMidfield,String awayMidfield,String expected) {
		
		List<Double> homeMidfieldEvaluations=Stream.of(homeMidfield.split(";")).map(Double::parseDouble)
		  .collect(Collectors.toList());
		
		List<Double> awayMidfieldEvaluations=Stream.of(awayMidfield.split(";")).map(Double::parseDouble)
				  .collect(Collectors.toList());
		
		MidfieldModifier mod = strategy.calculateModifier(homeMidfieldEvaluations, awayMidfieldEvaluations);
		
		assertTrue(expected.split(";")[0].equals(mod.getHomeTeamModifier().toString()));
		
		assertTrue(expected.split(";")[1].equals(mod.getAwayTeamModifier().toString()));
		
	}

}
