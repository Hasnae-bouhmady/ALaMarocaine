package com.bridgelabz.alamarocaine.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.alamarocaine.entity.Plat;

@Repository
public class IPlatImple implements IPlat {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Plat save(Plat platinformation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(platinformation);
		return platinformation;
	}

	@Override
	public List<Plat> getUsers() {
		Session currentSession = entityManager.unwrap(Session.class);
		List PlatList = currentSession.createQuery("from plat").getResultList();
		return PlatList;
	}
}
