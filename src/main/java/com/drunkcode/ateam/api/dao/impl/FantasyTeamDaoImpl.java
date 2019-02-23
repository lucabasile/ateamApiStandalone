package com.drunkcode.ateam.api.dao.impl;

import com.drunkcode.ateam.api.model.FantasyTeam;

public class FantasyTeamDaoImpl extends AbstractHibernateDao<FantasyTeam> {
	public FantasyTeamDaoImpl(){
		super();
		setClazz(FantasyTeam.class);
	}

}
