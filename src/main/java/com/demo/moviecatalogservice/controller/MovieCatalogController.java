package com.demo.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.moviecatalogservice.model.CatalogItem;
import com.demo.moviecatalogservice.model.Movie;
import com.demo.moviecatalogservice.model.Rating;
import com.demo.moviecatalogservice.model.UserRatings;
import com.demo.moviecatalogservice.service.MovieInfo;
import com.demo.moviecatalogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private RestTemplate template;

	@Autowired
	private WebClient.Builder builder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;

	@GetMapping("/")
	public String test() {
		return "Welcome";
	}

	@GetMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// get all rated movie IDs
		UserRatings ratings = userRatingInfo.getUserRating(userId);

		return ratings.getUserRatings().stream().map(rating -> {
			return movieInfo.getCatalogItem(rating);
		}).collect(Collectors.toList());
		
//		Movie movie = builder.build()
//			.get()
//			.uri("http://localhost:8082/movies/"+ rating.getMovieId())
//			.retrieve().bodyToMono(Movie.class)
//			.block();			

		// for each movie ID,call movie info service and get details

		// put them all together

	}

//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//		return Arrays.asList(new CatalogItem("No movie", "", 0));
//	}
}
