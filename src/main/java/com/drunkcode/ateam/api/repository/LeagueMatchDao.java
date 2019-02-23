package com.drunkcode.ateam.api.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.drunkcode.ateam.api.dao.impl.IOperations;
import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Team;

@Component
public interface LeagueMatchDao extends IOperations<LeagueMatch>{
	public LeagueMatch getNearestMatch();
	
	public List<LeagueMatch> getDailyMatches(LeagueDay day);
	
	public Calendar[]  getStartinAndEndingMatchesDatesBySeason(LeagueSeason season);
	
	LeagueMatch findMatch(LeagueDay day,LeagueSeason season,Team team);
}
