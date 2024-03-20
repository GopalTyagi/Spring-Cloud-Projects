package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;

	public User saveUser(User user) {
		return dao.add(user);
	}

}
