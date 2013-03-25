package com.home.main.db.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CommonDAOImpl implements CommonDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Fuzzy");
	EntityManager em = emf.createEntityManager();
	
	@Override
	public <ENTITY extends Serializable> ENTITY create(ENTITY entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.flush();
		em.getTransaction().commit();
		return entity;
	}

	@Override
	public <ENTITY extends Serializable> ENTITY findById(Class<ENTITY> clazz,
			Object objectid) {
		return em.find(clazz, (Serializable) objectid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ENTITY extends Serializable> Collection<ENTITY> findAll(
			Class<ENTITY> clazz) {
		Query query = em.createQuery("SELECT e FROM "+clazz.getSimpleName()+" e");
	    return (Collection<ENTITY>) query.getResultList();
	}

}
