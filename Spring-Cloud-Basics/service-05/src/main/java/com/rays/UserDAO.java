package com.rays;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public User add(User user) {
		entityManager.persist(user);
		return user;
	

	}
}
