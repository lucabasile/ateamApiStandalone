package com.drunkcode.ateam.api.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.repository.LeagueSeasonDao;

@Repository
@Component
@Transactional
public class LeagueSeasonDaoImpl extends AbstractHibernateDao<LeagueSeason> implements LeagueSeasonDao{
	public LeagueSeasonDaoImpl(){
		
		super();
		
		setClazz(LeagueSeason.class);
	}

	@Override
	public LeagueSeason getCurrentSeason() {
		List result = getCurrentSession().createCriteria(LeagueSeason.class).add(Restrictions.le("startingDate",Calendar.getInstance())).add(Restrictions.ge("endingDate", Calendar.getInstance())).list();
		if(result.size()>0)
			return (LeagueSeason)result.get(0);
		else
			return null;
	}
	
	

}
