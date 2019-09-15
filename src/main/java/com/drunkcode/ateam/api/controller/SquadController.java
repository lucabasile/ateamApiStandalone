package com.drunkcode.ateam.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drunkcode.ateam.api.dto.Squad;

@RestController
@RequestMapping("/squad")
public class SquadController {

	
	@GetMapping("/{leagueId}/{seasonId}/{day}/{teamId}")
	public void getSquad(@PathVariable long seasonId,@PathVariable Integer day,@PathVariable long teamId) {
		
	}
	
	@PostMapping("/{season}/{day}/{teamId}")
	public void createSquad(@PathVariable long seasonId,@PathVariable Integer day,@PathVariable long teamId,@RequestBody Squad squad) {
		
	}
	
	@PutMapping("/{season}/{day}/{teamId}")
	public void updateSquad(@PathVariable long seasonId,@PathVariable Integer day,@PathVariable long teamId,@RequestBody Squad squad) {
		
	}
}
