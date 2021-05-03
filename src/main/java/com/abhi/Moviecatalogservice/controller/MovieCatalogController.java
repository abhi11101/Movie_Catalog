package com.abhi.Moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.abhi.Moviecatalogservice.DAO.CatalogItem;
import com.abhi.Moviecatalogservice.DAO.Movie;
import com.abhi.Moviecatalogservice.DAO.Rating;
import com.abhi.Moviecatalogservice.DAO.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder builder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> list(@PathVariable("userId") String userid){
		
		
		UserRating userRating = restTemplate.getForObject("http://localhost:8082/ratingdata/users/" + userid, UserRating.class);
		
		
		return userRating.getUserRating().stream().map(rating -> {
	
			Movie movie =  restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
		/*	Movie movie = builder.build()
			.get()
			.uri("http://localhost:8081/movies/" + rating.getMovieId())
			.retrieve()
			.bodyToMono(Movie.class)
			.block();*/
			return	new CatalogItem(movie.getName(), "test", rating.getRating());
		}).collect(Collectors.toList());
		
	}
	
}
