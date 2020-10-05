package com.demo.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.moviecatalogservice.model.Rating;
import com.demo.moviecatalogservice.model.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "50"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "500"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000")
					}
					)
	public UserRatings getUserRating(String userId) {
		UserRatings rating = template.getForObject("http://movie-ratings-data-service/ratingsdata/users/" + userId,
				UserRatings.class);
		return rating;
//		return template.getForObject("http://movie-ratings-data-service/ratingsdata/users/" + userId,
//				UserRatings.class);
	}
	
	public UserRatings getFallbackUserRating(String userId) {
		UserRatings userRating = new UserRatings();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("0",0)));
		return userRating;
	}

}
