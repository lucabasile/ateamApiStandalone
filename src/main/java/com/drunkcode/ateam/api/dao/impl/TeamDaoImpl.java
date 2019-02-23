package com.drunkcode.ateam.api.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.Team;
import com.drunkcode.ateam.api.repository.TeamDao;

@Repository
@Component
@Transactional
public class TeamDaoImpl extends AbstractHibernateDao<Team> implements TeamDao{
	public TeamDaoImpl(){
		
		super();
		
		setClazz(Team.class);
	}

	@Override
	public Team findTeamByName(String name) {
		List result = getCurrentSession().createCriteria(Team.class).add(Restrictions.eq("name",name)).list();
		if(result.size()>0)
			return (Team)result.get(0);
		else
			return null;
	}
	
	

}
