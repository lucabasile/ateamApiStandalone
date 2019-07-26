package com.drunkcode.ateam.api.service.strategy.impl;

import static org.junit.Assert.assertTrue;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;

import com.drunkcode.ateam.api.service.dto.DefenseModifier;
import com.drunkcode.ateam.api.service.strategy.IDefenseModifierCalculationStrategy;

/**
 * 
 * @author BasileLu
 *
 */
public class DefenseModifierCalculationStrategy implements IDefenseModifierCalculationStrategy{

	private static final int LOWER_BOUND = -5;
	private static final double UPPER_BOUND = 4.0;
	private final static Double MEAN_RANGE_STEP=0.25;
	private final static Double MODIFIER_STEP=1.0;
	
	private final static Map<Integer, Double> zeroValuesMap = (Map<Integer, Double>) Stream.of(
			new AbstractMap.SimpleImmutableEntry<>(0, 6.75),
			new AbstractMap.SimpleImmutableEntry<>(1, 6.5),
			new AbstractMap.SimpleImmutableEntry<>(2, 6.25),
			new AbstractMap.SimpleImmutableEntry<>(3, 6.0),    
			new AbstractMap.SimpleImmutableEntry<>(4, 5.75),
			new AbstractMap.SimpleImmutableEntry<>(5, 5.5)
	    )
		.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
					  	
	@Override
	public DefenseModifier calculateModifier(ArrayList<Double> evaluations) {
		DefenseModifier defenseModifier = new DefenseModifier();
		if(evaluations.isEmpty()) {
			defenseModifier.setOpponentDefenseModifier(UPPER_BOUND);
			return defenseModifier;
		}
		OptionalDouble average = evaluations
	            .stream()
	            .mapToDouble(a -> a)
	            .average();
		assertTrue(average.isPresent());
		Double zeroValue = getZeroValue(evaluations.size());
		
		
		double calculatedValue = -Math.floor((average.getAsDouble()-zeroValue)/MEAN_RANGE_STEP)*MODIFIER_STEP;
		double usedValue=calculatedValue;
		if(calculatedValue>UPPER_BOUND) {
			usedValue=UPPER_BOUND;
		}
		if(calculatedValue<LOWER_BOUND) {
			usedValue=LOWER_BOUND;
		}
		defenseModifier.setOpponentDefenseModifier(usedValue);
		
		return defenseModifier;
	}
	
	/**
	 * The zeroValue is the value of the average that gives a modifier of ZERO
	 * this value is a function of the number of defenders
	 * @param numberOfDefenders
	 * @return
	 */
	private Double getZeroValue(int numberOfDefenders){
	
		Range numberOfDefendersRange= new IntRange(0, 6);
		
		assertTrue(numberOfDefendersRange.containsInteger(numberOfDefenders));
		
		return zeroValuesMap.get(numberOfDefenders);
		
	}
}
