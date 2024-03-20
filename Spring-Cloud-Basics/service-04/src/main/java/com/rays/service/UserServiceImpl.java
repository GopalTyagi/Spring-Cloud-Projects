package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;


@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	@Autowired
	public UserDAOInt dao;

	@Override
	public UserDTO delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO authenticate(String loginId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findByLogin(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object search(UserDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}
