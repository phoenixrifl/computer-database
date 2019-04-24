package main.java.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {
	
	protected Connection connect = null;
	
	public DAO(Connection conn) {
		this.connect = conn;
		
	}
	
	public DAO() {
		if(this.connect == null) {
						
			try {
				this.connect = DriverManager.getConnection(
							 "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
							 "admincdb",
							 "qwerty1234");
				
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			
		}
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T id);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract ArrayList<T> findAll();
}
