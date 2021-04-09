package com.MovieTracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.MovieTracker.dao.MovieServices;
import com.MovieTracker.dao.UserServices;
import com.MovieTracker.entity.Movie;
import com.MovieTracker.entity.User;

@Controller
@SessionAttributes("user")
public class MainController {
	UserServices us = new UserServices();
	MovieServices ms = new MovieServices();
	//handlers
	
	@ModelAttribute("user")
	public User users(){
		User u = new User();
		return u;
	}
	
	
	@RequestMapping("/login") // "/" -> this is the root or homepage
	public String loginErrorHandler() {
		return "html/login";
	}
	
	@RequestMapping("/") // "/" -> this is the root or homepage
	public String loginHandler() {
		return "html/login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logoutHandler() {
		return "html/login";
	}
	
	@RequestMapping("/movies") 
	public String movieHandler() {
		return "html/movies";
	}
	
	@RequestMapping("/favorites") 
	public String homeHandler(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("message", "");
		return "html/favorites";
	}
	
	@RequestMapping("/watch-list") 
	public String watchListHandler(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("message", "");
		return "html/watch-list";
	}
	
	//register user
	@PostMapping("/registerUser") //form element action  
	public ModelAndView registerHandler(HttpServletRequest request, @SessionAttribute("user") User u,
			@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("password") String pass,
			@RequestParam("gender") String gender, @RequestParam("dob") String dob) {
		HttpSession session=request.getSession();
		session.setAttribute("message", "");
		
		User newUser = new User(email, name, pass, gender, dob, new ArrayList<Movie>(), new ArrayList<Movie>());
		
		int result = us.registerUser(newUser);
		ModelAndView mav = new ModelAndView();
		
		if (result == 1) {
			mav.setViewName("html/movies");
	

			u.setEmail(newUser.getEmail());
			u.setPassword(newUser.getPassword());
			u.setDob(newUser.getDob());
			u.setName(newUser.getName());
			u.setGender(newUser.getGender());
			u.setFavorites(newUser.getFavorites());
			u.setWatchlist(newUser.getWatchlist());
		}
		
		else {
			mav.setViewName("html/invalid-registration");
		}
		return mav;
	}
	
	//user login
	@PostMapping(value = "/login") //form element action  
	public String loginHandler(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") 
	String pass, @SessionAttribute("user") User u) {
		HttpSession session=request.getSession();
		session.setAttribute("message", "");
		
		boolean result = us.validateUser(email, pass);

		if (result) {
			User resultUser = us.getUserByEmail(email);

			u.setEmail(resultUser.getEmail());
			u.setPassword(resultUser.getPassword());
			u.setDob(resultUser.getDob());
			u.setName(resultUser.getName());
			u.setGender(resultUser.getGender());
			u.setFavorites(resultUser.getFavorites());
			u.setWatchlist(resultUser.getWatchlist());
			return "html/movies";
		}

		else {
			return "html/invalid-login";
		}
	}
	
	@RequestMapping(value= "/addToFavorites", method = RequestMethod.GET) //form element action  
	public String addToFavsHandler(HttpServletRequest request, @SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
		
		System.out.println("__________________________________________________________________");
		HttpSession session=request.getSession();
		Movie m = new Movie();
		int mID = -1;
		int result = 0;
		
		//if movie exists, query for that movie using request params, take resultlist.get(0), 
		//call add to favorites with that Movie's id
		if (ms.movieExists(title, description, link)) {
			mID = ms.getMovieID(title, description, link);
			result = us.addMovieToFavorites(u.getEmail(), mID);
			
			if (result == 1) {
				session.setAttribute("message", "Movie successfully added to favorites!");
			}
			
			if (result == 0) {
				session.setAttribute("message", "Movie is already in your favorites page!");
			}
		}
		
		//if movie doesn't exist, set new movie attributes to request params
		//call add to favorites with new movie's id
		else {
			m.setDesc(description);
			m.setLink(link);
			m.setTitle(title);
			
			ms.addMovie(m);
			
			us.addMovieToFavorites(u.getEmail(), m.getId());
			
			session.setAttribute("message", "Movie successfully added to favorites!");
		}
		
		//Updates session user's favs to user favs in DB, if we go between adding movies from home and favs page it should update
		User resultUser = us.getUserByEmail(u.getEmail());
		u.setFavorites(resultUser.getFavorites());
		
		System.out.println("Message: "+ session.getAttribute("message"));
		
		return "html/movies";
	}
	
	@RequestMapping(value= "/addToWatchlist", method = RequestMethod.GET) //form element action  
	public String addToWatchHandler(HttpServletRequest request, @SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
		
		System.out.println("__________________________________________________________________");
		HttpSession session = request.getSession();
		int result = 0;
		Movie m = new Movie();
		int mID = -1;
		
		//if movie exists, query for that movie using request params, take resultlist.get(0), 
		//call add to favorites with that Movie's id
		if (ms.movieExists(title, description, link)) {
			mID = ms.getMovieID(title, description, link);
			result = us.addMovieToWatchlist(u.getEmail(), mID);
			
			if (result == 1) {
				session.setAttribute("message", "Movie successfully added to watchlist!");
			}
			
			if (result == 0) {
				session.setAttribute("message", "Movie is already in your watchlist page!");
			}
		}
		
		//if movie doesn't exist, set new movie attributes to request params
		//call add to favorites with new movie's id
		else {
			m.setDesc(description);
			m.setLink(link);
			m.setTitle(title);
			
			ms.addMovie(m);
			
			us.addMovieToWatchlist(u.getEmail(), m.getId());
			
			session.setAttribute("message", "Movie successfully added to watchlist!");
		}
		
		//Updates session user's favs to user favs in DB, if we go between adding movies from home and favs page it should update
		User resultUser = us.getUserByEmail(u.getEmail());
		u.setWatchlist(resultUser.getWatchlist());
		
		System.out.println("Message: "+ session.getAttribute("message"));
		
		return "html/movies";
	}
	
	@RequestMapping(value= "/removeFromFavorites", method = RequestMethod.GET) //form element action  
	public String removeFromFavsHandler(HttpServletRequest request, @SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
		
				HttpSession session=request.getSession();
				session.setAttribute("message", "");
		
				int mId = ms.getMovieID(title, description, link);
				
				us.deleteMovieFromFavorites(u.getEmail(), mId);
		
				//Updates session user's favs to user favs in DB, if we go between deleting movies from home and favs page it should update
				User resultUser = us.getUserByEmail(u.getEmail());
				u.setFavorites(resultUser.getFavorites());
		
		return "html/favorites";
	}
	
	@RequestMapping(value= "/removeFromWatchlist", method = RequestMethod.GET) //form element action  
	public String removeFromWatchlistHandler(HttpServletRequest request, @SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
		
				HttpSession session=request.getSession();
				session.setAttribute("message", "");
				
				int mId = ms.getMovieID(title, description, link);
				
				us.deleteMovieFromWatchlist(u.getEmail(), mId);
		
				//Updates session user's favs to user favs in DB, if we go between deleting movies from home and favs page it should update
				User resultUser = us.getUserByEmail(u.getEmail());
				u.setWatchlist(resultUser.getWatchlist());
		
		return "html/watch-list";
	}


}
