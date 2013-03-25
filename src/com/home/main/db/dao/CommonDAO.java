package com.home.main.db.dao;

import java.io.Serializable;
import java.util.Collection;

public interface CommonDAO {
	
	<ENTITY extends Serializable> ENTITY create(ENTITY entity);
	
	<ENTITY extends Serializable> ENTITY findById(Class<ENTITY> clazz, Object objectid);
	
	<ENTITY extends Serializable> Collection<ENTITY> findAll(Class<ENTITY> clazz);

}
