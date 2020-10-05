package com.demo.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.demo.moviecatalogservice.model.CatalogItem;
import com.demo.moviecatalogservice.model.User;
import com.demo.moviecatalogservice.model.UserRatings;
import com.demo.moviecatalogservice.service.MovieInfo;
import com.demo.moviecatalogservice.service.UserRatingInfo;
import com.demo.moviecatalogservice.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/catalog")
@ApiOperation(value = "/catalog")
public class MovieCatalogController {

	private static final Logger LOGGER = LogManager.getLogger(MovieCatalogController.class);

//	@Autowired
//	private RestTemplate template;

//	@Autowired
//	private WebClient.Builder builder;

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String test() {
		return "Welcome";
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "fetch all movie details for a particular user",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code =200, message = "SUCCESS", response = CatalogItem.class),
			@ApiResponse(code =401, message = "UNAUTHORIZED", response = CatalogItem.class),
			@ApiResponse(code =403, message = "FORBIDDEN", response = CatalogItem.class),
			@ApiResponse(code =404, message = "NOT FOUND", response = CatalogItem.class),
	})
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		final String METHODNAME = "getCatalog";
		LOGGER.info(METHODNAME + " STARTS");
		LOGGER.info("UserId received is {}", userId);
		try {
			User user = userService.validateUser(userId);
			if(user != null) {
				LOGGER.info("Userid {} exists ",userId);
				UserRatings ratings = userRatingInfo.getUserRating(userId);
				LOGGER.info(METHODNAME + " ENDS");
				return ratings.getUserRatings().stream().map(rating -> {
					return movieInfo.getCatalogItem(rating);
				}).collect(Collectors.toList());
			}
			else {
				throw new Exception("UserID does not exist");
				
			}
		} catch (Exception e) {
			CatalogItem item = new CatalogItem("USerID not found","",-1);
			List<CatalogItem> list = new ArrayList<>();
			list.add(item);
			LOGGER.error("Validating user failed with error : ", e);
			return list;
		}

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
