package com.drunkcode.ateam.api.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.PerformanceID;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;
import com.drunkcode.ateam.api.repository.PerformanceRepository;
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
	LeagueSeasonRepository leagueSeasonRepository;
		
	
	@Override
	public List<Performance> getPerformancesByParameters(Long startingYear,int startingDay,int endingDay,List<Long> ids){
//		List<Performance> result = new ArrayList<Performance>();
//		
//		List<Player> players =playerRepository.findAllById(ids);
//		List<Integer> dayIndexes= new ArrayList<Integer>();
//		LeagueSeason season = leagueSeasonRepository.findByStartingYear(startingYear);
//		for(int i =startingDay;i<=endingDay;i++) dayIndexes.add(i);
//		List<LeagueDay> days=leagueDayRepository.findBySeasonAndDayIndexIn(season, dayIndexes);
//		for(Player player: players){
//			for(LeagueDay day : days){
//				PerformanceID id= new PerformanceID();
//				id.setDay(day);
//				id.setPlayer(player);
//				id.setSeason(season);
//				Optional<Performance> performance = findById(id);
//				if(performance.isPresent()) {
//					result.add(performance.get());
//				}
//				// getPerfomanceByDayAndPlayer(day,player));
//			}
//		}
		return null;
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
