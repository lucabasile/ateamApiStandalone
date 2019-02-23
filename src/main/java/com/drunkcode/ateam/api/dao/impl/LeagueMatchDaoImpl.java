package com.drunkcode.ateam.api.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.dao.LeagueMatchDao;
import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Team;
@Repository
@Component
@Transactional
public class LeagueMatchDaoImpl extends AbstractHibernateDao<LeagueMatch> implements LeagueMatchDao {
	
	public LeagueMatchDaoImpl(){
		super();
		setClazz(LeagueMatch.class);
		
	}

	@Override
	public LeagueMatch getNearestMatch() {
		List result = getCurrentSession().createCriteria(LeagueMatch.class).add(Restrictions.ge("date",Calendar.getInstance())).addOrder(Order.asc("date")).list();
		if(result.size()>0)
			return (LeagueMatch)result.get(0);
		else
			return null;
		
	}

	@Override
	public List<LeagueMatch> getDailyMatches(LeagueDay day) {
		List result = getCurrentSession().createCriteria(LeagueMatch.class).add(Restrictions.eqOrIsNull("day", day)).list();
		if(result.size()>0)
			return result;
		else
			return null;
	}
	
	@Override
	public Calendar[]  getStartinAndEndingMatchesDatesBySeason(LeagueSeason season){
		List result = getCurrentSession().
				createCriteria(LeagueMatch.class).
					add(Restrictions.eq("season",season)).
					setProjection(Projections.projectionList().add(Projections.groupProperty("date"))).list();
		
		if(result.size()>0){
			Calendar[] dates = new Calendar[2];
			Calendar first =(Calendar)result.get(0);
			Calendar last = (Calendar)result.get(result.size()-1);
			dates[0]=first;
			dates[1]=last;
			
			return dates;
		}
			
		else
			return null;
	}
	@Override
	public LeagueMatch findMatch(LeagueDay day,LeagueSeason season,Team team){
		List result = getCurrentSession().createCriteria(LeagueMatch.class)
				.add(Restrictions.eqOrIsNull("day", day))
				.add(Restrictions.eq("season", season))
				.add(Restrictions.or(Restrictions.eqOrIsNull("homeTeam", team),Restrictions.eq("awayTeam", team)))
				.list();
		if(result.size()>0)
			return (LeagueMatch)result.get(0);
		else
			return null;
	}

}
