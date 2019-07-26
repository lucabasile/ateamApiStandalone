package com.drunkcode.ateam.api.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.util.StringUtils;

class DefenseModifierCalculationStrategyTestCase {
	
	DefenseModifierCalculationStrategy strategy = new DefenseModifierCalculationStrategy();

	@DisplayName("Check defense modifier calculation")
	@ParameterizedTest(name = "{index} => d1={0}, d2={1}, d3={2}, d4= {3}, d5= {4}, expected={5}")
	@CsvFileSource (resources="/defModTest.csv")
	void testCalculateModifier(String d1,String d2,String d3,String d4,String d5,String expected) {
		ArrayList<Double> evaluations = new ArrayList<Double>();
		if(!StringUtils.isEmpty(d1)) {
			evaluations.add( Double.valueOf(d1));
		}
		if(!StringUtils.isEmpty(d2)) {
			evaluations.add( Double.valueOf(d2));
		}
		if(!StringUtils.isEmpty(d3)) {
			evaluations.add( Double.valueOf(d3));
		}
		if(!StringUtils.isEmpty(d4)) {
			evaluations.add( Double.valueOf(d4));
		}
		if(!StringUtils.isEmpty(d5)) {
			evaluations.add( Double.valueOf(d5));
		}
			
		double rez = strategy.calculateModifier(evaluations).getOpponentDefenseModifier();
		System.out.println(d1+","+d2+","+d3+",expected : "+expected+" GOT : "+rez);
		assertTrue(rez==Double.valueOf(expected));
	}

}
