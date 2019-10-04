package com.drunkcode.ateam.api.dto.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.drunkcode.ateam.api.dto.validators.LeagueValidator;
import com.drunkcode.ateam.api.dto.validators.SquadValidator;

@Documented
@Constraint(validatedBy = SquadValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SquadConstraint {

}
