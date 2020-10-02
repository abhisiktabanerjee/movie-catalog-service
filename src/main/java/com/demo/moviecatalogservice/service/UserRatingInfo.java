package com.demo.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.moviecatalogservice.model.Rating;
import com.demo.moviecatalogservice.model.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRatings getUserRating(String userId) {
		return template.getForObject("http://movie-ratings-data-service/ratingsdata/users/" + userId,
				UserRatings.class);
	}
	
	public UserRatings getFallbackUserRating(String userId) {
		UserRatings userRating = new UserRatings();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("0",0)));
		return userRating;
	}

}
