package com.drunkcode.ateam.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Team;

public interface LeagueMatchRepository extends JpaRepository<LeagueMatch, Long>,LeagueMatchRepositoryCustom{
	LeagueMatch findByLeagueMatchId_DayAndLeagueMatchId_YearAndLeagueMatchId_HomeTeam(LeagueDay day,LeagueSeason season,Team team);
	LeagueMatch findByLeagueMatchId_DayAndLeagueMatchId_YearAndLeagueMatchId_AwayTeam(LeagueDay day,LeagueSeason season,Team team);
	List<LeagueMatch>  findByLeagueMatchId_DayAndLeagueMatchId_Year(LeagueDay day,LeagueSeason season);
	List<LeagueMatch> findByLeagueMatchId_Year(LeagueSeason season);
//
//	public LeagueMatch findByDayAndSeasonAndHomeTeam(LeagueDay day,LeagueSeason season,Team team);
//	public LeagueMatch findByDayAndSeasonAndAwayTeam(LeagueDay day,LeagueSeason season,Team team);
//	public List<LeagueMatch> findByDayAndSeason(LeagueDay day,LeagueSeason season);
}
