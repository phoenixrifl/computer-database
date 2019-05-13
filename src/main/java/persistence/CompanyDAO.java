package main.java.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import main.java.modele.Company;

public class CompanyDAO {
	
	private static final String SQL_PAGE = "SELECT * FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_SELECT= "SELECT * FROM company";
	private static final String SQL_SELECT_ONE_COMPANY = "SELECT id,name FROM company WHERE id =";

	private static CompanyDAO instance = null;
	
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	private String config = "/home/excilys/computer-database/src/main/resources/hikari.properties";
	private HikariConfig configHakari = new HikariConfig(config);
	private HikariDataSource hikariDataSource = new HikariDataSource(configHakari);
	
	private CompanyDAO() {
	}
	
	public final static CompanyDAO getInstance() {
		if(CompanyDAO.instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}
	
	public Company find(long id) {
		Company tmp = null;
		try(Connection connect = hikariDataSource.getConnection();PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT_ONE_COMPANY);) {
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.err.println(resultSet.getInt("id")+ " "+resultSet.getString("name"));
				
				tmp = new Company(resultSet.getInt("id"),
						resultSet.getString("name"));
			}
		} catch (SQLException e) {
			logger.error("impossible de trouver la compagnie recherchée");
		}
		return tmp;
	}
	

	public ArrayList<Company> findAll(int limits, int offset) {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try(Connection connect = hikariDataSource.getConnection();PreparedStatement preparedStatement = connect.prepareStatement(SQL_PAGE);) {
			
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
		try(Connection connect = hikariDataSource.getConnection();PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT)) {
			
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