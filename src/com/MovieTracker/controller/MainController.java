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
		
		//if movie exists, query for that movie using request params, take resultlist.get(0), 
		//call add to favorites with that Movie's id
		
		
		
		
		//if movie doesn't exist, set new movie attributes to request params
		//call add to favorites with new movie's id
		System.out.println("title: " + title);
		System.out.println("desc: " + description);
		System.out.println("link: " + link);
		m.setDesc(description);
		m.setLink(link);
		m.setTitle(title);
		
		ms.addMovie(m);
		
		System.out.println("ID: " + m.getId());
		
		us.addMovieToFavorites(u.getEmail(), m.getId());
		
		return "html/movies";
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
