package com.drunkcode.ateam.api.repository.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.LeagueDay;
import com.drunkcode.ateam.api.model.Team;
import com.drunkcode.ateam.api.repository.TeamRepositoryCustom;

@Repository
@Component
@Transactional
public class TeamRepositoryImpl extends AbstractRepositoryImpl implements TeamRepositoryCustom{
	

	@Override
	public Team findTeamByName(String name) {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Team> criteria = builder.createQuery(Team.class);
		Root<Team> team = criteria.from(Team.class);
		getCurrentSession().beginTransaction();
		List<Team> result = getCurrentSession().createQuery(criteria.where(builder.equal(team.get("name"),name))).list();
		//List result = getCurrentSession().createCriteria(Team.class).add(Restrictions.eq("name",name)).list();
		if(result.size()>0)
			return (Team)result.get(0);
		else
			return null;
	}
	
	

}
