package com.drunkcode.ateam.api.dto.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.drunkcode.ateam.api.dto.validators.LeagueValidator;

@Documented
@Constraint(validatedBy = LeagueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TeamsConstraint {
	String message() default "Teams set not valid for this league";

}
