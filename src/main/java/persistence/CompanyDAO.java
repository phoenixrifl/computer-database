package main.java.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.java.modele.Company;

public class CompanyDAO extends DAO<Company>{
	
	private static final String SQL_PAGE = "SELECT * FROM company ORDER BY id LIMIT ? OFFSET ?";

	
	public CompanyDAO(Connection conn) {
		super(conn);
	}
	
	public CompanyDAO() {
		super();
	}

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Company> findAll() {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");
			while(result.next()) {
				tmp = new Company(
						result.getInt("id"),
						result.getString("name"));
				company_list.add(tmp);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return company_list;
	}

	public ArrayList<Company> findAll(int limits, int offset) {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_PAGE);
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
			e.printStackTrace();
		}
		return company_list;
	}
	
}