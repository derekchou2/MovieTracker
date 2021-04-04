package com.MovieTracker.dao;

import com.MovieTracker.entity.Movie;

public interface MovieI {
	public int addMovie(Movie m);
	public boolean movieExists(String title, String descript, String link);
	
	/**
	 * Queries movie table in databse for movie id with given parameters
	 * @param title
	 * @param descript
	 * @param link
	 * @return id of movie found in database, -1 if movie is not found
	 */
	public int getMovieID(String title, String descript, String link);
}
