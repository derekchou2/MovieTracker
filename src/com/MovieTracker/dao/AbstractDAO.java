package com.MovieTracker.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractDAO {
	
	private final String persistenceUnitName = "MovieTracker";
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	

	public void connect() {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}
	
	public void dispose() {
		em.close();
		emf.close();
	}
}
