package com.MovieTracker.dao;

import java.util.List;

import javax.persistence.Query;

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

	@Override
	public boolean movieExists(String title, String descript, String link) {
		boolean result = false;
		
		try {
			connect();

			//DQL, don't need transaction			
			
			
			//Search from joined table in database
			Query q = em.createNativeQuery("SELECT * FROM movietracker.movie WHERE title = ?1 AND description = ?2 AND link = ?3");
			q.setParameter(1, title);
			q.setParameter(2, descript);
			q.setParameter(3, link);

			
			List results = q.getResultList();
			int size = results.size();
			
			if (size > 0) {
				result = true;
				System.out.println("Movie: " + title +  " already exists");
			}
			else {
				System.out.println("Movie: " + title +  " does not exist");
			}


		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dispose();
		}
		return result;
	}

	@Override
	public int getMovieID(String title, String descript, String link) {
		int result = -1;
		
		try {
			connect();

			//DQL, don't need transaction			
			
			
			//Search from joined table in database
			Query q = em.createNativeQuery("SELECT id FROM movietracker.movie WHERE title = ?1 AND description = ?2 AND link = ?3");
			q.setParameter(1, title);
			q.setParameter(2, descript);
			q.setParameter(3, link);

			
			List results = q.getResultList();
			int size = results.size();
			
			if (size > 0) {
				result = (int)results.get(0);
				System.out.println("Got movie with id: " + (int)results.get(0));
			}
			else {
				System.out.println("Movie not found");
			}


		}catch(Exception e) {
			result = -1;
			e.printStackTrace();
		}finally {
			dispose();
		}
		return result;
	}

}
