package com.drunkcode.ateam.api.service.strategy.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class DefenseModifierCalculationStrategyTestCase {
	
	DefenseModifierCalculationStrategy strategy = new DefenseModifierCalculationStrategy();

	@DisplayName("Check defense modifier calculation")
	@ParameterizedTest(name = "{index} => d1={0}, d2={1}, d3={2}, expected={3}")
	@CsvFileSource (resources="/defModTest_3.csv")
	void testCalculateModifier(Double d1,Double d2,Double d3,Double expected) {
		ArrayList<Double> evaluations = new ArrayList<Double>() {{
			add( d1);
			add( d2);
			add( d3);
			}};
		double rez = strategy.calculateModifier(evaluations).getOpponentDefenseModifier();
		System.out.println(d1+","+d2+","+d3+",expected : "+expected+" GOT : "+rez);
		assertTrue(rez==expected);
	}
	
	@DisplayName("Check defense modifier calculation")
	@ParameterizedTest(name = "{index} => d1={0}, d2={1}, d3={2}, d4= {3}, expected={4}")
	@CsvFileSource (resources="/defModTest_4.csv")
	void testCalculateModifier5(Double d1,Double d2,Double d3,Double d4,Double expected) {
		ArrayList<Double> evaluations = new ArrayList<Double>() {{
			add( d1);
			add( d2);
			add( d3);
			add( d4);
			}};
		double rez = strategy.calculateModifier(evaluations).getOpponentDefenseModifier();
		System.out.println(d1+","+d2+","+d3+","+d4+",expected : "+expected+" GOT : "+rez);
		assertTrue(rez==expected);
	}
	
	@DisplayName("Check defense modifier calculation")
	@ParameterizedTest(name = "{index} => d1={0}, d2={1}, d3={2}, d4= {3}, d5= {4}, expected={5}")
	@CsvFileSource (resources="/defModTest_5.csv")
	void testCalculateModifier5(Double d1,Double d2,Double d3,Double d4,Double d5,Double expected) {
		ArrayList<Double> evaluations = new ArrayList<Double>() {{
			add( d1);
			add( d2);
			add( d3);
			add( d4);
			add( d5);
			}};
		double rez = strategy.calculateModifier(evaluations).getOpponentDefenseModifier();
		System.out.println(d1+","+d2+","+d3+","+d4+","+d5+",expected : "+expected+" GOT : "+rez);
		assertTrue(rez==expected);
	}

}
