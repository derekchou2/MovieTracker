package com.MovieTracker.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.MovieTracker.dao.UserServices;
import com.MovieTracker.entity.Movie;
import com.MovieTracker.exception.IncompleteMovieException;
import com.MovieTracker.dao.MovieServices;


public class ServicesTest {
	UserServices us = new UserServices();
	MovieServices ms = new MovieServices();
	
	@Test
	void testValidateUserPass() {
		assertTrue(us.validateUser("derekchou2@gmail.com", "password1"));
	}
	
	@Test
	void testValidateUserFail() {
		assertFalse(us.validateUser("derekchou2@gmail.com", "asdfjkl;"));
	}
	
	@Test
	void testGetUserByEmail() {
		assertEquals("Amanda", us.getUserByEmail("alim@gmail.com").getName());
	}
	
	@Test
	void testGetAllUsers() {
		assertEquals(7, us.getAllUsers().size());
	}
	
	@Test
	void testAddAndDeleteMovie() {
		int favSize = us.getUserByEmail("chou.d@northeastern.edu").getFavorites().size();
		
		us.addMovieToFavorites("chou.d@northeastern.edu", 152);
		assertEquals(favSize + 1, us.getUserByEmail("chou.d@northeastern.edu").getFavorites().size());
		
		us.deleteMovieFromFavorites("chou.d@northeastern.edu", 152);
		assertEquals(favSize, us.getUserByEmail("chou.d@northeastern.edu").getFavorites().size());
		
	}
	
	@Test
	void testInvalidAddAndDeleteMovie() {
		assertEquals(0,us.addMovieToFavorites("chou.d@northeastern.edu", 9999999));
		assertEquals(0,us.deleteMovieFromFavorites("chou.d@northeastern.edu", 9999999));
		assertEquals(0,us.addMovieToWatchlist("chou.d@northeastern.edu", 9999999));
		assertEquals(0,us.deleteMovieFromWatchlist("chou.d@northeastern.edu", 9999999));
	}
	
	@Test
	void testValidMovieSearch() {
		assertEquals(true, ms.movieExists("Spirited Away", "A young girl, Chihiro, becomes trapped in a strange new world of spirits."
				+ " When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
				"/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg"));
	}
	
	@Test
	void testInvalidMovieSearch() {
		assertEquals(false, ms.movieExists("this", "movie", "doesn't exist"));
	}
	
	@Test
	void testValidGetMovieID() {
		assertEquals(12, ms.getMovieID("Spirited Away", "A young girl, Chihiro, becomes trapped in a strange new world of spirits."
				+ " When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.",
				"/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg"));
	}
	
	@Test
	void testInvalidGetMovieID() {
		assertEquals(-1, ms.getMovieID("this", "movie", "doesn't exist"));
	}
	
	@Test
	void testIncompleteMovie() {
		assertThrows(IncompleteMovieException.class, () -> {
			IncompleteMovieException.validate("Movie", "Description", "https://image.tmdb.org/t/p/w1280null");
	    });
	}

}
