package com.rays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User")
public class UserCtl {

	@Autowired
	private UserService service;

	@PostMapping
	public User saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

}
