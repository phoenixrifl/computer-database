package persistence;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modele.Computer;

public class ComputerDAO extends DAO<Computer> {
	
	private final String SQL_INSERT = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (NULL,?,?,?,?);";
	private final String SQL_DELETE = "DELETE FROM computer WHERE id= ";
	private final String SQL_SELECT = "SELECT * FROM computer";
	private final String SQL_SELECT_ONE = "SELECT * FROM computer WHERE id = ";
	private final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ";
	
	public ComputerDAO(Connection conn) {
		super(conn);
	}
	
	public ComputerDAO() {
		super();
	}
	

	@Override
	public boolean create(Computer obj) {

		
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_INSERT);
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, obj.getIntroduced());
			preparedStatement.setObject(3, obj.getDiscontinued());
			preparedStatement.setObject(4, obj.getCompany_id());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_DELETE+obj.getId_()+";");
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean update(Computer obj) {
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_UPDATE+obj.getId_()+";");
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, obj.getIntroduced());
			preparedStatement.setObject(3, obj.getDiscontinued());
			preparedStatement.setObject(4, obj.getCompany_id());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public ArrayList<Computer> findAll(){
		ArrayList<Computer> computer_list = new ArrayList<Computer>();
		Computer tmp = null;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL_SELECT);
			while(result.next()) {
					Date introduced = result.getDate("introduced");
					LocalDate convDate = null;
					if(introduced !=null) {
						convDate = introduced.toLocalDate();
					}
					Date discontinued = result.getDate("discontinued");
					LocalDate convDate1 = null;
					if(discontinued != null) {
						convDate1 = discontinued.toLocalDate();
					}
					tmp = new Computer(
							result.getInt("id"),
							result.getString("name"),
							convDate,
							convDate1,
							result.getInt("company_id"));
					computer_list.add(tmp);
				}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return computer_list;
	}

	@Override
	public Computer find(int id) {
		Computer tmp = null;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL_SELECT_ONE+id);
			if(result.first()) {
				Date introduced = result.getDate("introduced");
				LocalDate convDate = null;
				if(introduced !=null) {
					convDate = introduced.toLocalDate();
				}
				Date discontinued = result.getDate("discontinued");
				LocalDate convDate1 = null;
				if(discontinued != null) {
					convDate1 = discontinued.toLocalDate();
				}
				tmp = new Computer(
						result.getInt("id"),
						result.getString("name"),
						convDate,
						convDate1,
						result.getInt("company_id"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	
}
