package com.drunkcode.ateam.api.dao.impl;

import com.drunkcode.ateam.api.model.User;

public class UserDaoImpl extends AbstractHibernateDao<User>{
	
	public UserDaoImpl(){
		super();
		setClazz(User.class);
	}

}
