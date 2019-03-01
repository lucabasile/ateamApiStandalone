package com.drunkcode.ateam.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.PerformanceID;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.LeagueMatchRepository;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;
import com.drunkcode.ateam.api.repository.PerformanceRepository;
import com.drunkcode.ateam.api.repository.PlayerRepository;


@RestController
@RequestMapping("/players")
public class CoreData {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	PerformanceRepository performanceRepository;
	
	@Autowired
	LeagueSeasonRepository leagueSeasonRepository;
	
	@Autowired
	LeagueDayRepository leagueDayRepository;
	
	@Autowired
	LeagueMatchRepository leagueMatchRepository;
	
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
	public @ResponseBody List<Performance> getPerformances(@RequestParam("startingYear") Long startingYear,@RequestParam("startingDay") int startingDay,@RequestParam("endingDay") int endingDay,@RequestParam("ids") Long[] ids){
		
		List<Long> idList = new ArrayList<Long>();
		for(Long id : ids)idList.add(id);
		 
List<Performance> result = new ArrayList<Performance>();
		
		List<Player> players =playerRepository.findByPlayerIdIn(idList);
		List<Integer> dayIndexes= new ArrayList<Integer>();
		LeagueSeason season = leagueSeasonRepository.findByStartingYear(startingYear);
		for(int i =startingDay;i<=endingDay;i++) dayIndexes.add(i);
		List<LeagueDay> days=leagueDayRepository.findBySeasonAndDayIndexIn(season, dayIndexes);
		//, players);
		
		for(Player player: players){
			for(LeagueDay day : days){
				List<LeagueMatch> matches = leagueMatchRepository.findByDayAndSeason(day, season);
				List<LeagueMatch> playedMatches = matches.stream().filter(m->m.getPlayers().contains(player)).collect(Collectors.toList());
				
				playedMatches.stream().forEach(pm->{
					PerformanceID id= new PerformanceID();
					id.setDay(day);
					id.setPlayer(player);
					id.setSeason(season);
					id.setMatch(pm);
					Optional<Performance> performance = performanceRepository.findById(id);
					if(performance.isPresent()) {
						result.add(performance.get());
					}
				});
				// getPerfomanceByDayAndPlayer(day,player));
			}
		}
			
		return result;//performanceRepository.getPerformancesByParameters(startingYear,startingDay, endingDay, idList);
	} 
	
	
	@GetMapping("/id/{listOfIds}")
	public @ResponseBody List<Player> getByIds(@PathVariable List<Long> listOfIds){
	
		return playerRepository.findAllById(listOfIds);
	}
}
