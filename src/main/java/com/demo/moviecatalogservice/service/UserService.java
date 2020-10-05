package com.demo.moviecatalogservice.service;

import org.springframework.stereotype.Service;

import com.demo.moviecatalogservice.model.User;

@Service
public interface UserService {
	
	public User validateUser(String userid);
	

}
