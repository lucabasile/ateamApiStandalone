package com.drunkcode.ateam.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class FantasySeason {
	
	@Id
	Long id;

	LeagueSeason season;
	
	Integer startingDay;
	
	Integer endingDay;
	
}
