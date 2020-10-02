package com.demo.moviecatalogservice.model;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRatings {
	
	private String userId;
	private List<Rating> userRatings;
	
	public void initData(String userId) {
		this.setUserId(userId);
		this.setUserRatings(Arrays.asList(new Rating("100",3),new Rating("200",4)));
	}

}
