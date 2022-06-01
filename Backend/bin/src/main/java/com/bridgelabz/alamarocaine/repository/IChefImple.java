package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.Chef;

@Repository
public class IChefImple implements IChef {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Chef save(Chef chefinformation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(chefinformation);
		return chefinformation;
	}

	@Override
	public List<Chef> getUsers() {
		Session currentSession = entityManager.unwrap(Session.class);
		List ChefList = currentSession.createQuery("from chef").getResultList();
		return ChefList;
	}
}
