package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class FantasySeason {

	LeagueSeason season;
	
	Integer startingDay;
	
	Integer endingDay;
	
}
