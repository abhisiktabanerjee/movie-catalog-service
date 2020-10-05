package com.demo.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.moviecatalogservice.DAO.UserDAO;
import com.demo.moviecatalogservice.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public User validateUser(String userid) {
		return userDao.findByUserid(userid);

	}

}
