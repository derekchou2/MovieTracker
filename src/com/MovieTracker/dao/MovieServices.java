package com.MovieTracker.dao;

import com.MovieTracker.entity.Movie;

public class MovieServices extends AbstractDAO implements MovieI {

	public MovieServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addMovie(Movie m) {
		int result = 0;
		
		try {
		connect();
		
		//DML action, must create transaction
		em.getTransaction().begin();
		em.persist(m);
		em.getTransaction().commit();
		result = 1;
		System.out.println("Added movie: " + m.getTitle());
		
		}catch(Exception e) {
			result = 0;
			System.out.println("Unable to add movie: " + m.getTitle());
			return result;
		}finally {
			dispose();
		}
		return result;
	}

}
