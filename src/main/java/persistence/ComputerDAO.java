package main.java.persistence;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.modele.Computer;

public class ComputerDAO{
	
	private final String SQL_INSERT = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (NULL,?,?,?,?);";
	private final String SQL_DELETE = "DELETE FROM computer WHERE id= ";
	private final String SQL_SELECT_ONE = "SELECT * FROM computer WHERE id = ";
	private final String SQL_UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ";
	private static final String SQL_PAGE = "SELECT * FROM computer ORDER BY id LIMIT ? OFFSET ?";
	
	private static ComputerDAO instance = null;
	protected Connection connect = null;

	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	
	private ComputerDAO() {
		if(this.connect == null) {
			
			try {
				this.connect = DriverManager.getConnection(
							 "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",
							 "admincdb",
							 "qwerty1234");
				
				
				} catch (SQLException e) {
					logger.info("connexion impossible");
				}
				
			
		}
	}
	
	public final static ComputerDAO getInstance() {
		if(ComputerDAO.instance == null) {
			instance = new ComputerDAO();
		}
		return instance;
	}
	
	

	public boolean create(Computer obj) {

		
		try(PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_INSERT)) {
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, obj.getIntroduced());
			preparedStatement.setObject(3, obj.getDiscontinued());
			preparedStatement.setObject(4, obj.getCompany_id());
			
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException ex) {
			logger.error("impossible de créer cet ordinateur");
		}
		return false;
	}

	public boolean delete(Computer obj) {
		
		try(PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_DELETE+obj.getId_()+";")) {
			
			preparedStatement.executeUpdate();
			return true;
		}catch(SQLException ex) {
			logger.error("suppression impossible de l'ordinateur d'id"+obj.getId_());
		}
		return false;
	}

	
	public boolean update(Computer obj) {
		try(PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_UPDATE+obj.getId_()+";")) {
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, obj.getIntroduced());
			preparedStatement.setObject(3, obj.getDiscontinued());
			preparedStatement.setObject(4, obj.getCompany_id());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException ex) {
			logger.error("update impossible");
		}
		return false;
	}
		
	public Computer find(int id) {
		Computer tmp = null;
		try(ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(SQL_SELECT_ONE+id)) {
			
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
		} catch (SQLException ex) {
			logger.error("Ordinateur "+id+" introuvable ");
		}
		return tmp;
	}

	public ArrayList<Computer> findAll(int limits, int offset) {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		Computer tmp = null;

		try(PreparedStatement preparedStatement = this.connect.prepareStatement(SQL_PAGE)) {
			
			preparedStatement.setLong(1, limits);
			preparedStatement.setLong(2, offset);
			ResultSet result = preparedStatement.executeQuery();
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
				computers.add(tmp);
			
				
			}
		}catch(SQLException ex) {
			logger.error("Echec liste d'ordinateurs");
		}
		return computers;
	}
	
	
}
