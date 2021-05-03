package com.abhi.Moviecatalogservice.DAO;

import java.util.List;

public class UserRating {

	public List<Rating> userRating;
	
	public UserRating() {
		
	}
	
	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
}
