package com.drunkcode.ateam.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drunkcode.ateam.api.dao.LeagueMatchDao;
import com.drunkcode.ateam.api.dao.LeagueSeasonDao;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;



@Controller
@RequestMapping("/scheduleData")
public class Schedule {
	
	@Autowired
	LeagueMatchDao matchDao;
	
	@Autowired
	LeagueDayRepository dayDao;
	
	@Autowired
	LeagueSeasonDao seasonDao;
	
	@RequestMapping(value="/{day}",method = RequestMethod.GET)
	public @ResponseBody List<LeagueMatch> getSchedule(@PathVariable int day){
		LeagueSeason season = seasonDao.getCurrentSeason();
		return matchDao.getDailyMatches(dayDao.findByDayIndex(day, season));
		
	}
	
	@RequestMapping(value="/nextMatch",method = RequestMethod.GET)
	public @ResponseBody LeagueMatch getNextMatch(){
		LeagueMatch nextMatch =matchDao.getNearestMatch();
		return nextMatch;
	}
}
