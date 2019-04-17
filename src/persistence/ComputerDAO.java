package persistence;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import modele.Computer;

public class ComputerDAO extends DAO<Computer> {
	

	public ComputerDAO(Connection conn) {
		super(conn);
	}
	
	public ComputerDAO() {
		super();
	}
	

	@Override
	public boolean create(Computer obj) {
		String sql = "INSERT INTO computer (id,name,introduced,discontinued,company_id)";
		sql+="VALUES (?,?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(sql);
			preparedStatement.setObject(1, preparedStatement.getGeneratedKeys());
			preparedStatement.setObject(2, obj.getName());
			preparedStatement.setObject(3, obj.getIntroduced());
			preparedStatement.setObject(4, obj.getDiscontinued());
			preparedStatement.setObject(5, obj.getCompany_id());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Computer obj) {
		String sql = "DElETE * FROM computer WHERE name="+obj.getName()+";";
		try {
			PreparedStatement preparedStatement = this.connect.prepareStatement(sql);
			preparedStatement.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<Computer> findAll(){
		ArrayList<Computer> computer_list = new ArrayList<Computer>();
		Computer tmp = null;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			while(result.next()) {
					Date intro = result.getDate("introduced");
					LocalDate convDate = null;
					if(intro !=null) {
						convDate = intro.toLocalDate();
					}
					Date disco = result.getDate("discontinued");
					LocalDate convDate1 = null;
					if(disco != null) {
						convDate1 = disco.toLocalDate();
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
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id = "+id);
			if(result.first()) {
				Date intro = result.getDate("introduced");
				LocalDate convDate = null;
				if(intro !=null) {
					convDate = intro.toLocalDate();
				}
				Date disco = result.getDate("discontinued");
				LocalDate convDate1 = null;
				if(disco != null) {
					convDate1 = disco.toLocalDate();
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
