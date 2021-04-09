package com.MovieTracker.exception;

public class IncompleteMovieException extends Exception {

	public IncompleteMovieException(String s) {
		super(s);
	}
	
	 public static void validate(String title, String desc, String link)throws IncompleteMovieException{  
	     if(title.equals("") || desc.equals("") || link.equals("https://image.tmdb.org/t/p/w1280null"))  {
	    	 throw new IncompleteMovieException("The Movie Database API is missing one or more attributes for this movie!");
	     }
	      

	 }

}
