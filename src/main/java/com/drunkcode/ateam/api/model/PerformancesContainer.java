package com.drunkcode.ateam.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PerformancesContainer {
	
	Integer id;
	String name;
	
	List<Performance> playerPerfomances;


	
	

}
