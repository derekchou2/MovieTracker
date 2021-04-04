package com.MovieTracker.controller;

import java.util.List;

import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public User users() {
		return new User();
	}
	
	@RequestMapping("/") // "/" -> this is the root or homepage
	public String loginHandler() {
		return "html/login";
	}
	
	@RequestMapping("/logout") 
	public String logoutHandler() {
		return "html/login";
	}
	
	@RequestMapping("/movies") 
	public String movieHandler() {
		return "html/movies";
	}
	
	@RequestMapping("/favorites") 
	public String homeHandler(@SessionAttribute("user") User u) {
		return "html/favorites";
	}
	
	@RequestMapping("/watch-list") 
	public String watchListHandler() {
		return "html/watch-list";
	}
	
	//register user
	@RequestMapping("/registerUser") //form element action  
	public ModelAndView registerHandler(@ModelAttribute User user, @SessionAttribute("user") User u) {
		int result = us.registerUser(user);
		ModelAndView mav = new ModelAndView();
		
		if (result == 1) {
			mav.setViewName("html/movies");
			mav.addObject(user); 
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			u.setDob(user.getDob());
			u.setName(user.getName());
			u.setGender(user.getGender());
			u.setFavorites(user.getFavorites());
			u.setWatchlist(user.getWatchlist());
		}
		
		else {
			mav.setViewName("html/invalid-registration");
		}
		return mav;
	}
	
	@RequestMapping(value= "/addToFavorites", method = RequestMethod.GET) //form element action  
	public String addToFavsHandler(@SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
		
		System.out.println("__________________________________________________________________");
		Movie m = new Movie();
		int mID = -1;
		
		System.out.println(description);
		
		//if movie exists, query for that movie using request params, take resultlist.get(0), 
		//call add to favorites with that Movie's id
		if (ms.movieExists(title, description, link)) {
			mID = ms.getMovieID(title, description, link);
			us.addMovieToFavorites(u.getEmail(), mID);
		}
		
		//if movie doesn't exist, set new movie attributes to request params
		//call add to favorites with new movie's id
		else {
			m.setDesc(description);
			m.setLink(link);
			m.setTitle(title);
			
			ms.addMovie(m);
			
			us.addMovieToFavorites(u.getEmail(), m.getId());
		}
		
		//Updates session user's favs to user favs in DB, if we go between adding movies from home and favs page it should update
		User resultUser = us.getUserByEmail(u.getEmail());
		u.setFavorites(resultUser.getFavorites());
		
		return "html/movies";
	}
	
	@RequestMapping(value= "/removeFromFavorites", method = RequestMethod.GET) //form element action  
	public String removeFromFavsHandler(@SessionAttribute("user") User u, @RequestParam("title") String title,
			@RequestParam("description") String description, @RequestParam("link") String link) {
				int mId = ms.getMovieID(title, description, link);
				
				us.deleteMovieFromFavorites(u.getEmail(), mId);
		
				//Updates session user's favs to user favs in DB, if we go between deleting movies from home and favs page it should update
				User resultUser = us.getUserByEmail(u.getEmail());
				u.setFavorites(resultUser.getFavorites());
		
		return "html/favorites";
	}


	//user login
	@RequestMapping(value = "/login", method = RequestMethod.GET) //form element action  
	public String loginHandler(@RequestParam("email") String email, @RequestParam("password") 
	String pass, @SessionAttribute("user") User u) {
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



}
