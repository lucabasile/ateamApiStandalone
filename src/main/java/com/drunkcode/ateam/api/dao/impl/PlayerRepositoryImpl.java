package com.drunkcode.ateam.api.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.drunkcode.ateam.api.model.Player;
import com.drunkcode.ateam.api.repository.PlayerRepositoryCustom;

@Repository
@Transactional
@Component
public class PlayerRepositoryImpl  implements PlayerRepositoryCustom{
	
	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    public SessionFactory getSessionFactory() {
         return entityManagerFactory.unwrap(SessionFactory.class);
    }
    protected final Session getCurrentSession() {
   	 return getSessionFactory().getCurrentSession();
   }

	@Transactional
	public List<Player> findPlayersByRole(String role) {
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Player> criteria = builder.createQuery(Player.class);
		Root<Player> player = criteria.from(Player.class);
		getCurrentSession().beginTransaction();
		List<Player> result = getCurrentSession().createQuery(criteria.where(builder.equal(player.get("role"),role))).list();
		if(result.size()>0)
			return result;
		else
			return Collections.emptyList();
	}


	@Override
	@SuppressWarnings("unused")
	public List<Player> findPlayersByListOfId(List<Long> ids) {
		List<Player> result = getCurrentSession().createCriteria(Player.class).add(Restrictions.in("playerId",ids)).list();
		return result;
	}
}
