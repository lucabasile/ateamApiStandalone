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
@RequestMapping("/league")
public class LeagueController {

	@GetMapping("/{leagueId}")
	public void getLeague(@PathVariable long seasonId) {
		
	}
	
	@PostMapping("/{leagueId}")
	public void createLeague(@PathVariable long seasonId,@RequestBody Squad squad) {
		
	}
	
	@PutMapping("/{leagueId}")
	public void updateLeague(@PathVariable long seasonId,@RequestBody Squad squad) {
		
	}
}
