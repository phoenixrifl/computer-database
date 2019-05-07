package main.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.modele.Company;

public class CompanyDAO {
	
	private static final String SQL_PAGE = "SELECT * FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_SELECT= "SELECT * FROM company";


	private static CompanyDAO instance = null;
	
//	protected Connection connect = null;
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	
	private CompanyDAO() {
		try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			try {
//				this.connect = DriverManager.getConnection(
//							 "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
//							 "admincdb",
//							 "qwerty1234");
//				
//				
//				} catch (SQLException e) {
//					logger.error("connexion impossible à la DB compagnie");
//				}
//				
//			
//		}
	}
	
	public final static CompanyDAO getInstance() {
		if(CompanyDAO.instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}

	public ArrayList<Company> findAll(int limits, int offset) {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try(Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC",
				 "admincdb",
				 "qwerty1234");PreparedStatement preparedStatement = connect.prepareStatement(SQL_PAGE);) {
			
			preparedStatement.setLong(1, limits);
			preparedStatement.setLong(2, offset);
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				tmp = new Company(
						result.getInt("id"),
						result.getString("name"));
				company_list.add(tmp);
			}

			
		}catch(SQLException e) {
			logger.error("pagination impossible");
		}
		return company_list;
	}
	
	public ArrayList<Company> findAll() {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try(Connection connect = DriverManager.getConnection(
				 "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC",
				 "admincdb",
				 "qwerty1234");PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT)) {
			
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				tmp = new Company(
						result.getInt("id"),
						result.getString("name"));
				company_list.add(tmp);
			}

		}catch(SQLException e) {
			logger.error("liste compagnie impossible");
		}
		return company_list;
	}
	
}