package com.drunkcode.ateam.api.repository.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.LeagueMatch;
import com.drunkcode.ateam.api.model.LeagueSeason;
import com.drunkcode.ateam.api.model.Team;
import com.drunkcode.ateam.api.repository.LeagueMatchRepositoryCustom;


@Repository
@Component
@Transactional
public class LeagueMatchRepositoryImpl extends AbstractRepositoryImpl implements LeagueMatchRepositoryCustom  {
	

	@Override
	public LeagueMatch getNearestMatch() {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<LeagueMatch> criteria = builder.createQuery(LeagueMatch.class);
		Root<LeagueMatch> leagueMatch = criteria.from(LeagueMatch.class);
		getCurrentSession().beginTransaction();
		List<LeagueMatch> result =getCurrentSession().createQuery(criteria.where(builder.greaterThan(leagueMatch.get("date"),Calendar.getInstance()))).list();
		//List result = getCurrentSession().createCriteria(LeagueMatch.class).add(Restrictions.ge("date",Calendar.getInstance())).addOrder(Order.asc("date")).list();
		if(result.size()>0)
			return (LeagueMatch)result.get(0);
		else
			return null;
		
	}

	@Override
	public List<LeagueMatch> getDailyMatches(LeagueDay day) {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<LeagueMatch> criteria = builder.createQuery(LeagueMatch.class);
		Root<LeagueMatch> leagueMatch = criteria.from(LeagueMatch.class);
		getCurrentSession().beginTransaction();
		List<LeagueMatch> result = getCurrentSession().createQuery(criteria.where(builder.equal(leagueMatch.get("day"),day))).list();
		//List result = getCurrentSession().createCriteria(LeagueMatch.class).add(Restrictions.eqOrIsNull("day", day)).list();
		if(result.size()>0)
			return result;
		else
			return null;
	}
	
//	@Override
//	public Calendar[]  getStartinAndEndingMatchesDatesByYear(LeagueSeason season){
//		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<LeagueMatch> criteria = builder.createQuery(LeagueMatch.class);
//		Root<LeagueMatch> leagueMatch = criteria.from(LeagueMatch.class);
//		getCurrentSession().beginTransaction();
//
//		List<LeagueMatch> result = getCurrentSession().createQuery(criteria.where(builder.equal(leagueMatch.get("season"),season))).list();
//
////		List result = getCurrentSession().
////				createCriteria(LeagueMatch.class).
////					add(Restrictions.eq("season",season)).
////					setProjection(Projections.projectionList().add(Projections.groupProperty("date"))).list();
//
//		if(result.size()>0){
//			List<Calendar> alldates = result.stream().map(LeagueMatch::getDate).collect(Collectors.toList());
//
//			Calendar[] dates = new Calendar[2];
//			Calendar first =Collections.max(alldates);;
//			Calendar last = Collections.min(alldates);
//			dates[0]=first;
//			dates[1]=last;
//
//			return dates;
//		}
//
//		else
//			return null;
//	}
	@Override
	public LeagueMatch findMatch(LeagueDay day,LeagueSeason season,Team team){
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<LeagueMatch> cq = builder.createQuery(LeagueMatch.class);
		Root<LeagueMatch> leagueMatch = cq.from(LeagueMatch.class);
		getCurrentSession().beginTransaction();
		List<LeagueMatch> result =getCurrentSession().createQuery(cq.where(builder.and(builder.and(
				builder.equal(leagueMatch.get("season"),season), builder.equal(leagueMatch.get("day"),day))),
				builder.or(builder.equal(leagueMatch.get("homeTeam"),team),
						builder.equal(leagueMatch.get("awayTeam"), team)))).list();
//		criteria.where(builder.equal(leagueMatch.get("season"),season))
//		List<LeagueMatch> result = getCurrentSession().createQuery(criteria
//				.where(builder.and(builder.equal(leagueMatch.get("season"),season)).a);
//				.where(builder.or(
//						builder.equal(leagueMatch.get("homeTeam"),team),
//						builder.equal(leagueMatch.get("awayTeam"), team)
//						)).
//				where(builder.equal(leagueMatch.get("day"),day))
//						).list();
//		List result = getCurrentSession().createCriteria(LeagueMatch.class)
//				.add(Restrictions.eqOrIsNull("day", day))
//				.add(Restrictions.eq("season", season))
//				.add(Restrictions.or(Restrictions.eqOrIsNull("homeTeam", team),Restrictions.eq("awayTeam", team)))
//				.list();
		if(result.size()>0)
			return (LeagueMatch)result.get(0);
		else
			return null;
	}

}
