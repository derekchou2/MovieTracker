package com.MovieTracker.main;

import java.util.ArrayList;
import java.util.List;

import com.MovieTracker.dao.MovieServices;
import com.MovieTracker.dao.UserServices;
import com.MovieTracker.entity.Movie;
import com.MovieTracker.entity.User;


public class mainRunner {

	public static void main(String[] args) {
		MovieServices ms = new MovieServices();
		UserServices us = new UserServices();
		
		Movie m = new Movie(1, "test", "test", "test");
		User u = new User("testtttt@test.com", "name", "pass", "Male", "1995-08-10", new ArrayList<Movie>(), new ArrayList<Movie>());
		
		
		us.addMovieToFavorites("alim@gmail.com", 1);
	}

}
