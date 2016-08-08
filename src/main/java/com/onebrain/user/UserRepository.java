package com.onebrain.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

@Stateless
public class UserRepository {
	
	//@PersistenceContext(unitName = "h2db")
	private EntityManager entityManager;
	
	public UserRepository(){}
	
	public User findById(){
		entityManager.clear();
		return null;
	}

}
