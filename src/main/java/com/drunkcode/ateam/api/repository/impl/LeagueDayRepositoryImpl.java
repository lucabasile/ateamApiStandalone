package com.drunkcode.ateam.api.repository.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.repository.LeagueDayRepositoryCustom;
@Repository
@Component
@Transactional
public class LeagueDayRepositoryImpl extends AbstractRepositoryImpl implements LeagueDayRepositoryCustom {
	
	
	public LeagueDay findByDayIndex(int dayIndex,LeagueSeason season) {
		List result = getCurrentSession().createCriteria(LeagueDay.class).add(Restrictions.eq("dayIndex",dayIndex)).add(Restrictions.eq("season",season)).list();
		if(result.size()>0)
			return (LeagueDay)result.get(0);
		else
			return null;
	}
	
	public List<LeagueDay> findByDayIndex(List<Integer> dayIndexes,LeagueSeason season) {
		List<LeagueDay> result = getCurrentSession().createCriteria(LeagueDay.class).add(Restrictions.in("dayIndex",dayIndexes)).add(Restrictions.eq("season",season)).list();
		return result;
	}
}
