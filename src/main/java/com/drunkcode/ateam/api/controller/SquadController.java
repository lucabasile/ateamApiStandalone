package com.drunkcode.ateam.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/squad")
public class SquadController {

	
	@GetMapping("/{season}/{day}/{teamId}")
	public void getSquad() {
		
	}
}
