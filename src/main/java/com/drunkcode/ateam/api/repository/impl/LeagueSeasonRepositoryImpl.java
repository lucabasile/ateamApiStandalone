//package com.drunkcode.ateam.api.repository.impl;
//
//import java.util.Calendar;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.drunkcode.ateam.api.model.LeagueSeason;
//import com.drunkcode.ateam.api.repository.LeagueSeasonRepository;
//import com.drunkcode.ateam.api.repository.LeagueSeasonRepositoryCustom;
//
//@Repository
//@Component
//@Transactional
//public class LeagueSeasonRepositoryImpl extends AbstractRepositoryImpl implements LeagueSeasonRepositoryCustom{
//	
//	@Override
//	public LeagueSeason getCurrentSeason() {
//		//List result = getCurrentSession().createCriteria(LeagueSeason.class).add(Restrictions.le("startingDate",Calendar.getInstance())).add(Restrictions.ge("endingDate", Calendar.getInstance())).list();
//		Calendar future = Calendar.getInstance();
//		future.set(Calendar.MONTH, Calendar.JULY);
//		LeagueSeason currentSeason = findByStartingDateAfterAndEndingDateBefore(Calendar.getInstance(),future);
//		return currentSeason;
//	}
//	
//	
//
//}
