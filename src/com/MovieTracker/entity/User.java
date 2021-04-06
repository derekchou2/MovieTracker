package com.MovieTracker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class User {
		
	@Id
	private String email;
	
	@Basic
	private String name;
	
	@Basic
	private String password;
	
	@Basic
	private String gender;
	
	@Basic
	private String dob;
	
	@OneToMany(targetEntity = Movie.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_favorites")
	private List<Movie> favorites;
	
	@OneToMany(targetEntity = Movie.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "user_watchlist")
	private List<Movie> watchlist;
	
	
	
	
	public User() {
		super();
		this.favorites = new ArrayList<Movie>();
		this.watchlist = new ArrayList<Movie>();
	}
	
	public User(String email, String name, String password, String gender, String dob, List<Movie> f, List<Movie> w) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.favorites = f;
		this.watchlist = w;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	public List<Movie> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Movie> favorites) {
		this.favorites = favorites;
	}

	public List<Movie> getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(List<Movie> watchlist) {
		this.watchlist = watchlist;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((favorites == null) ? 0 : favorites.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((watchlist == null) ? 0 : watchlist.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (favorites == null) {
			if (other.favorites != null)
				return false;
		} else if (!favorites.equals(other.favorites))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (watchlist == null) {
			if (other.watchlist != null)
				return false;
		} else if (!watchlist.equals(other.watchlist))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + ", gender=" + gender + ", dob="
				+ dob + ", favorites=" + favorites + ", watchlist=" + watchlist + "]";
	}
	
	

}
