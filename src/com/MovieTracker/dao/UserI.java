package com.MovieTracker.dao;

import java.util.List;

import com.MovieTracker.entity.Movie;
import com.MovieTracker.entity.User;


public interface UserI {
	public List<User> getAllUsers();
	public User getUserByEmail(String email);
	public boolean validateUser(String email, String pass);
	public int registerUser(User u);
	public int addMovieToFavorites(String uEmail, int mId);
	public int deleteMovieFromFavorites(String uEmail, int mId);
	public int addMovieToWatchlist(String uEmail, int mId);

}
