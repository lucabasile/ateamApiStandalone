package com.drunkcode.ateam.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.PerformanceRepository;
import com.drunkcode.ateam.api.repository.PlayerRepository;


@RestController
@RequestMapping("/players")
public class CoreData {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	PerformanceRepository performanceRepository;
	
	@Transactional
	@GetMapping(value="/{role}")
	public List<Player> getAllPlayers(@PathVariable String role){
		List<Player> requestedPlayers;
		if("*".equalsIgnoreCase(role)){
			requestedPlayers = playerRepository.findAll();
		}else{
			requestedPlayers= playerRepository.findPlayersByRole(role);
		}
			
		return requestedPlayers;
	} 
	
	@Transactional
	@GetMapping(value="/performances")
	public @ResponseBody List<Performance> getPerformances(@RequestParam("startingDay") int startingDay,@RequestParam("endingDay") int endingDay,@RequestParam("ids") Long[] ids){
		
		List<Long> idList = new ArrayList<Long>();
		for(Long id : ids)idList.add(id);
		 
			
		return performanceRepository.getPerformancesByParameters(startingDay, endingDay, idList);
	} 
}
