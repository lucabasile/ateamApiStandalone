package com.drunkcode.ateam.api.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
@Transactional
@Component
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperations<T> {
    private Class<T> clazz;
    
    
    //iniettiamo direttamente la sessionFactory nel Dao
    //non sono più necessari gli hibernateTemplate che si usavano prima in Spring
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    public SessionFactory getSessionFactory() {
         return entityManagerFactory.unwrap(SessionFactory.class);
    }

    // API
    
    //per operare correttamente ogni costruttore dovr� provvedere a chiamare questo metodo
    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Override
    public final T findOne(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Override
    public final List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public final void create(final T entity) {
        Preconditions.checkNotNull(entity);
        // getCurrentSession().persist(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    @Override
    public final void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Override
    public final void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected final Session getCurrentSession() {
    	 return getSessionFactory().getCurrentSession();
    }

}
