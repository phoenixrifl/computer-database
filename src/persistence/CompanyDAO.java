package persistence;

import java.sql.Connection;

import modele.Company;

public class CompanyDAO extends DAO<Company>{
	
	public CompanyDAO(Connection conn) {
		super(conn);
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
	
}
