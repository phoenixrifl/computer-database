package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import modele.Computer;

public class ComputerDAO extends DAO<Computer> {
	
	public ComputerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Computer find(int id) {
		Computer computer = new Computer();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = "+id);
			if(result.first()) {
				computer = new Computer(
						id,
						result.getString("name"),
						result.getDate("introduced"),
						result.getDate("discontinued"),
						result.getInt("company_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return computer;
	}
	
	
}
