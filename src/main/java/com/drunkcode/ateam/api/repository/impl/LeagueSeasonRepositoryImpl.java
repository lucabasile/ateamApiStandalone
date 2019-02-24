package com.drunkcode.ateam.api.repository.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.repository.LeagueSeasonRepositoryCustom;

@Repository
@Component
@Transactional
public class LeagueSeasonRepositoryImpl extends AbstractRepositoryImpl implements LeagueSeasonRepositoryCustom{


	@Override
	public LeagueSeason getCurrentSeason() {
		List result = getCurrentSession().createCriteria(LeagueSeason.class).add(Restrictions.le("startingDate",Calendar.getInstance())).add(Restrictions.ge("endingDate", Calendar.getInstance())).list();
		if(result.size()>0)
			return (LeagueSeason)result.get(0);
		else
			return null;
	}
	
	

}
