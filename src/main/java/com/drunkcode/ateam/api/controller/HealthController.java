package com.drunkcode.ateam.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	
	@RequestMapping(value="/healthCheck",method = RequestMethod.GET)
	public String Hi() {
		return "Hello loser!";
	}
}
