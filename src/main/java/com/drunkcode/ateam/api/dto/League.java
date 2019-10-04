package com.drunkcode.ateam.api.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.drunkcode.ateam.api.dto.validators.annotations.TeamsConstraint;

import lombok.Data;

@Data
public class League {
	@NotNull
	Long id;
	
	@NotEmpty
	String name;
	
	@TeamsConstraint
	List<Long> usersIds;
	
	@NotNull
	Integer startingYear;
	
	@NotNull
	Integer firstDayOfPlay;

}
