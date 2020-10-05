package com.demo.moviecatalogservice.DAO;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.moviecatalogservice.model.User;

@Repository
@Transactional
public interface UserDAO extends JpaRepository<User, String> {
	
	User findByUserid(String userid);  

}
