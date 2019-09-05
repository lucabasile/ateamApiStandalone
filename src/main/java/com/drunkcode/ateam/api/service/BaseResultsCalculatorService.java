package com.drunkcode.ateam.api.service;

import com.drunkcode.ateam.api.service.dto.MatchResult;

import lombok.Data;

@Data
public class BaseResultsCalculatorService implements ResultsCalculatorService{
	
	private int firstGoalThreshold;

	@Override
	public MatchResult calculateMatchResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
