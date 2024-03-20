package com.rays.service03.ctl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAOInt{

	@PersistenceContext
	protected EntityManager entityManager;
	
	public long add(UserDTO dto) {
		entityManager.persist(dto);
		return dto.getId() ;
		
	}
	}
	
	

