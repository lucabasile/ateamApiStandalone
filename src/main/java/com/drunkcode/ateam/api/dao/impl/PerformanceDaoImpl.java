package com.drunkcode.ateam.api.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.dao.LeagueSeasonDao;
import com.drunkcode.ateam.api.dao.PerformanceDao;
import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Performance;
import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.LeagueDayRepository;
import com.drunkcode.ateam.api.repository.PlayerRepository;


@Repository
@Component
@Transactional
public class PerformanceDaoImpl extends AbstractHibernateDao<Performance>implements PerformanceDao{
	
	@Autowired
	PlayerRepository playerDao;
	
	@Autowired
	LeagueDayRepository dayDao;
	
	@Autowired
	LeagueSeasonDao seasonDao;
	
	public PerformanceDaoImpl(){
		super();
		
		setClazz(Performance.class);
	}
	
	@Override
	public List<Performance> getPerformancesByParameters(int startingDay,int endingDay,List<Long> ids){
		List<Performance> result = new ArrayList<Performance>();
		
		List<Player> players =playerDao.findPlayersByListOfId(ids);
		List<Integer> dayIndexes= new ArrayList<Integer>();
		LeagueSeason season = seasonDao.getCurrentSeason();
		for(int i =startingDay;i<=endingDay;i++) dayIndexes.add(i);
		List<LeagueDay> days=dayDao.findByDayIndex(dayIndexes, season);
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
