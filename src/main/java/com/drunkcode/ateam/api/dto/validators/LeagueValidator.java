package com.drunkcode.ateam.api.dto.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.dto.League;
import com.drunkcode.ateam.api.dto.validators.annotations.TeamsConstraint;

@Component
public class LeagueValidator implements ConstraintValidator<TeamsConstraint, League>{
	
	

	@Override
	public boolean isValid(League league, ConstraintValidatorContext context) {
		//league.get
		return false;
	}

}
