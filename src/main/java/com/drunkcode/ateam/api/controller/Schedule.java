package com.drunkcode.ateam.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.LeagueMatchRepository;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;



@Controller
@RequestMapping("/scheduleData")
public class Schedule {
	
	@Autowired
	LeagueMatchRepository leagueMatchRepository;
	
	@Autowired
	LeagueDayRepository leagueDayRepository;
	
	@Autowired
	LeagueSeasonRepository leagueSeasonRepository;
	
	@RequestMapping(value="/{day}",method = RequestMethod.GET)
	public @ResponseBody List<LeagueMatch> getSchedule(@PathVariable int day){
		LeagueSeason season = leagueSeasonRepository.getCurrentSeason();
		return leagueMatchRepository.getDailyMatches(leagueDayRepository.findByDayIndex(day, season));
		
	}
	
	@RequestMapping(value="/nextMatch",method = RequestMethod.GET)
	public @ResponseBody LeagueMatch getNextMatch(){
		LeagueMatch nextMatch =leagueMatchRepository.getNearestMatch();
		return nextMatch;
	}
}
