package com.drunkcode.ateam.api.repository.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractRepositoryImpl {
	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    public SessionFactory getSessionFactory() {
         return entityManagerFactory.unwrap(SessionFactory.class);
    }
    protected final Session getCurrentSession() {
   	 return getSessionFactory().getCurrentSession();
   }
}
