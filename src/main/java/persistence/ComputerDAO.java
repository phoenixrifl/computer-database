package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import exception.SqlCommandeException;
import modele.Company;
import modele.Computer;

public class ComputerDAO {

	private static final String SQL_INSERT = "INSERT INTO computer (id,name,introduced,discontinued,company_id) VALUES (NULL,?,?,?,?);";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id= ?";
	private static final String SQL_SELECT_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id= ?";
	private static final String SQL_UPDATE = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id =";
	private static final String SQL_PAGE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY computer.id LIMIT ? OFFSET ?";
	private static final String SQL_SELECT = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ";
	private static final String SQL_COUNT = "SELECT COUNT(*) AS total FROM computer";
	private static final String SQL_SEARCH = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.id LIMIT ? OFFSET ?";
	private static final String SQL_COUNT_SEARCH = "SELECT COUNT(*) AS total FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR  company.name LIKE ? ";

	private static ComputerDAO instance = null;

	private CompanyDAO companyDAO = CompanyDAO.getInstance();
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource hikariDataSource;

	 static {
		 ResourceBundle bundle = ResourceBundle.getBundle("hikari");
		 config.setDriverClassName(bundle.getString("driverClassName"));
		 config.setJdbcUrl(bundle.getString("jdbcUrl"));
		 config.setUsername(bundle.getString("username"));
		 config.setPassword(bundle.getString("password"));
		 config.addDataSourceProperty( "cachePrepStmts" , "true" );
		 config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		 config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		 hikariDataSource = new HikariDataSource(config);
		 }
	private ComputerDAO() {
	}

	public final static ComputerDAO getInstance() {
		if (ComputerDAO.instance == null) {
			instance = new ComputerDAO();
		}
		return instance;
	}

	public boolean create(Computer obj) {

		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_INSERT)) {
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, Date.valueOf(obj.getIntroduced()));
			preparedStatement.setObject(3, Date.valueOf(obj.getDiscontinued()));
			if (obj.getCompany() == null || obj.getCompany().getId_() == 0)
				preparedStatement.setObject(4, null);
			else
				preparedStatement.setObject(4, obj.getCompany().getId_());

			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException ex) {
			logger.error("impossible de créer cet ordinateur", ex);

		}
		return false;
	}

	public boolean delete(Computer obj) {

		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_DELETE)) {
			preparedStatement.setInt(1, obj.getId_());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException ex) {
			logger.error("suppression impossible de l'ordinateur d'id" + obj.getId_());
		}
		return false;
	}

	public boolean update(Computer obj) {
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_UPDATE + obj.getId_() + ";")) {
			preparedStatement.setObject(1, obj.getName());
			preparedStatement.setObject(2, Date.valueOf(obj.getIntroduced()));
			preparedStatement.setObject(3, Date.valueOf(obj.getDiscontinued()));
			if (obj.getCompany() == null || obj.getCompany().getId_() == -1) {
				preparedStatement.setObject(4, null);
			} else {
				preparedStatement.setObject(4, obj.getCompany().getId_());
			}
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException ex) {
			logger.error("update impossible");
		}
		return false;
	}

	public Computer find(int id) {
		Computer computer = new Computer();
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT_ONE)) {
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if (result.first()) {
				Date introduced = result.getDate("computer.introduced");
				LocalDate convDate = null;
				if (introduced != null) {
					convDate = introduced.toLocalDate();
				}
				Date discontinued = result.getDate("computer.discontinued");
				LocalDate convDate1 = null;
				if (discontinued != null) {
					convDate1 = discontinued.toLocalDate();
				}
				computer = new Computer(result.getInt("computer.id"), result.getString("computer.name"), convDate,
						convDate1, result.getInt("company.id"), result.getString("company.name"));

			}

		} catch (SQLException ex) {
			logger.error("Ordinateur " + id + " introuvable ");
		}
		return computer;
	}

	public int count() {
		int computerMax = 0;
		try (Connection connect = hikariDataSource.getConnection();
				ResultSet resultSet = connect.createStatement().executeQuery(SQL_COUNT)) {
			if (resultSet.first()) {
				computerMax = resultSet.getInt("total");
			}
		} catch (SQLException ex) {
			logger.error("echec count");
		}
		return computerMax;
	}

	public int countSearch(String search) {
		int computerSearchMax = 0;
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_COUNT_SEARCH);) {
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.first()) {
				computerSearchMax = resultSet.getInt("total");
			}
		} catch (SQLException ex) {
			logger.error("echec countSearch");
		}

		return computerSearchMax;
	}

	public ArrayList<Computer> findAll() throws SqlCommandeException {
		ArrayList<Computer> computers = new ArrayList<Computer>();

		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT)) {
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Date introduced = result.getDate("introduced");
				LocalDate convDate = null;
				if (introduced != null) {
					convDate = introduced.toLocalDate();
				}
				Date discontinued = result.getDate("discontinued");
				LocalDate convDate1 = null;
				if (discontinued != null) {
					convDate1 = discontinued.toLocalDate();
				}
				Computer computer = new Computer();

				computer.setId_(result.getInt("computer.id"));
				computer.setName(result.getString("computer.name"));
				computer.setIntroduced(convDate);
				computer.setDiscontinued(convDate1);
				int company_id = result.getInt("company_id");
				if (company_id != 0) {
					Company company = companyDAO.find(company_id);
					// company.setName(companyDAO.find(company_id).getName());
					computer.setCompany(company);
				} else
					computer.setCompany(null);
				computers.add(computer);
			}
		} catch (SQLException ex) {
			logger.error("Echec liste d'ordinateurs "+ ex.getMessage());
			throw new SqlCommandeException(SQL_SELECT);
		}
		return computers;
	}

	public ArrayList<Computer> findAll(int limits, int offset) throws SqlCommandeException {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		Computer computer = null;

		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_PAGE)) {

			preparedStatement.setLong(1, limits);
			preparedStatement.setLong(2, offset);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				Date introduced = result.getDate("introduced");
				LocalDate convDate = null;
				if (introduced != null) {
					convDate = introduced.toLocalDate();
				}
				Date discontinued = result.getDate("discontinued");
				LocalDate convDate1 = null;
				if (discontinued != null) {
					convDate1 = discontinued.toLocalDate();
				}
				computer = new Computer(result.getInt("computer.id"), result.getString("computer.name"), convDate,
						convDate1, result.getInt("company.id"), result.getString("company.name"));

				computers.add(computer);

			}
		} catch (SQLException ex) {
			logger.error("Echec liste d'ordinateurs "+ ex.getMessage());
			throw new SqlCommandeException(SQL_PAGE);
		}
		return computers;
	}

	public ArrayList<Computer> searchComputers(String search, int limits, int offset) throws SqlCommandeException {
		ArrayList<Computer> computers = new ArrayList<Computer>();
		Computer computer = null;

		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_SEARCH)) {
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			preparedStatement.setLong(3, limits);
			preparedStatement.setLong(4, offset);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Date introduced = resultSet.getDate("introduced");
				LocalDate convDate = null;
				if (introduced != null) {
					convDate = introduced.toLocalDate();
				}
				Date discontinued = resultSet.getDate("discontinued");
				LocalDate convDate1 = null;
				if (discontinued != null) {
					convDate1 = discontinued.toLocalDate();
				}
				computer = new Computer(resultSet.getInt("computer.id"), resultSet.getString("computer.name"), convDate,
						convDate1, resultSet.getInt("company.id"), resultSet.getString("company.name"));
				computers.add(computer);
			}
		} catch (SQLException ex) {
			logger.error("Echec de la recherche "+ ex.getMessage());
			throw new SqlCommandeException(SQL_SEARCH);
		}
		return computers;
	}

}
