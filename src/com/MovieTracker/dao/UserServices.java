package com.MovieTracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.MovieTracker.entity.Movie;
import com.MovieTracker.entity.User;




public class UserServices extends AbstractDAO implements UserI {

	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			// 1. connect
			connect();
			
			// 2. execute
			em.getTransaction().begin();
			users = em.createQuery("SELECT s FROM Student s", User.class).getResultList(); // JPQL
			em.getTransaction().commit();
			
		} catch(Exception e) {
			users = null;
			e.printStackTrace();
		}
		
		// 3. close
		dispose();
		
		if (users.isEmpty() || users == null) {
			System.out.println("No users in system");
		}
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		User u = null;
		
		try {
			connect();
			
			//Query, dont need transaction
			
			u = em.find(User.class, email);
			System.out.println("Name: " + u.getName());
			System.out.println("Email: " + u.getEmail());
	
			
			}catch(Exception e) {
				System.out.println("No user associated with that email");
				return u;
			}finally {
				dispose();
			}
		return u;
	}

	@Override
	public boolean validateUser(String email, String pass) {
		boolean result = false;
		User u = null;
		try {
			connect();
			
			//Query, dont need transaction
			
			u = em.find(User.class, email);
			result = u.getPassword().equals(pass);
			
			
			}catch(Exception e) {
				result = false;
			}finally {
				dispose();
			}
		System.out.println(result ? "Login Successful" : "User email and password do not match!");
		return result;
	}

	@Override
	public int registerUser(User u) {
		int result = 0;
		
		try {
		connect();
		
		//DML action, must create transaction
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		result = 1;
		System.out.println("Added user: " + u.getEmail());
		
		}catch(Exception e) {
			result = 0;
			System.out.println("Unable to add user: " + u.getEmail());
			return result;
		}finally {
			dispose();
		}
		return result;
	}

	@Override
	public int addMovieToFavorites(String uEmail, int mId) {
		int result = 0;
		User u = null;
		Movie m = null;

		try {
			connect();

			//DML, need transaction
			em.getTransaction().begin();
			
			//Do student and course both exist?
			u = em.find(User.class, uEmail);
			m = em.find(Movie.class, mId);
			
			if (u == null || m == null) {
				throw new IllegalArgumentException("Invalid user or movie ID, movie not added to favorites");
			}
			
			//Search from joined table in database
			Query q = em.createNativeQuery("SELECT * FROM movietracker.user_favorites WHERE User_EMAIL = ?1 AND favorites_ID = ?2");
			q.setParameter(1, uEmail);
			q.setParameter(2, mId);
			
			List results = q.getResultList();
			int size = results.size();
			
			if (size == 0) {
				u.getFavorites().add(m);
				System.out.println("Movie added to favorites: " + m.getTitle());
				result = 1;
			}
			else {
				System.out.println("Movie is already in favorites");
				result = 0;
			}
			em.getTransaction().commit();


		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}finally {
			dispose();
		}
		return result;

	}

	@Override
	public int addMovieToWatchlist(String uEmail, int mId) {
		int result = 0;
		User u = null;
		Movie m = null;

		try {
			connect();

			//DML, need transaction
			em.getTransaction().begin();
			
			//Do student and course both exist?
			u = em.find(User.class, uEmail);
			m = em.find(Movie.class, mId);
			
			if (u == null || m == null) {
				throw new IllegalArgumentException("Invalid user or movie ID, movie not added to watchlist");
			}
			
			//Search from joined table in database
			Query q = em.createNativeQuery("SELECT * FROM movietracker.user_watchlist WHERE User_EMAIL = ?1 AND watchlist_ID = ?2");
			q.setParameter(1, uEmail);
			q.setParameter(2, mId);
			
			List results = q.getResultList();
			int size = results.size();
			
			if (size == 0) {
				u.getFavorites().add(m);
				System.out.println("Movie added to watchlist: " + m.getTitle());
				result = 1;
			}
			else {
				System.out.println("Movie is already in watchlist");
				result = 0;
			}
			em.getTransaction().commit();


		}catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}finally {
			dispose();
		}
		return result;

	}

	@Override
	public int deleteMovieFromFavorites(String uEmail, int mId) {
		int result = 0;
		
		
		try {
			connect();
			
			//DML action, must create transaction
			em.getTransaction().begin();
			
			User u = em.find(User.class, uEmail);
			
			Query q = em.createNativeQuery("DELETE FROM movietracker.user_favorites WHERE User_EMAIL = ?1 AND favorites_ID = ?2");
			q.setParameter(1, uEmail);
			q.setParameter(2, mId);
			result = q.executeUpdate();
			System.out.println("Movie with ID " + mId + " has been deleted");

			
			em.getTransaction().commit();
			
			}catch(Exception e) {
				result = 0;
				System.out.println("Movie was not found, nothing was deleted");
				return result;
			}finally {
				dispose();
			}
		return result;
	}


}
