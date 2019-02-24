package com.drunkcode.ateam.api.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;
import com.drunkcode.ateam.api.repository.PerformanceRepositoryCustom;
import com.drunkcode.ateam.api.repository.PlayerRepository;


@Repository
@Component
@Transactional
public class PerformanceRepositoryImpl extends AbstractRepositoryImpl implements PerformanceRepositoryCustom {
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	LeagueDayRepository leagueDayRepository;
	
	@Autowired
	LeagueSeasonRepository leagueSeasonDao;
	
	
	@Override
	public List<Performance> getPerformancesByParameters(int startingDay,int endingDay,List<Long> ids){
		List<Performance> result = new ArrayList<Performance>();
		
		List<Player> players =playerRepository.findPlayersByListOfId(ids);
		List<Integer> dayIndexes= new ArrayList<Integer>();
		LeagueSeason season = leagueSeasonDao.getCurrentSeason();
		for(int i =startingDay;i<=endingDay;i++) dayIndexes.add(i);
		List<LeagueDay> days=leagueDayRepository.findByDayIndex(dayIndexes, season);
		for(Player player: players){
			for(LeagueDay day : days){
				result.add(getPerfomanceByDayAndPlayer(day,player));
			}
		}
		return result;
	}
	
	public Performance getPerfomanceByDayAndPlayer(LeagueDay day,Player player){
		List result = getCurrentSession().createCriteria(Performance.class).add(Restrictions.eq("day",day)).add(Restrictions.eq("player",player)).list();
		if(result.size()>0){
			return (Performance)result.get(0);
		}else{
			return null;
		}
	}
}
