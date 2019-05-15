package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import exception.SqlCommandeException;
import modele.Company;

public class CompanyDAO {

	private static final String SQL_PAGE = "SELECT * FROM company ORDER BY id LIMIT ? OFFSET ?";
	private static final String SQL_SELECT = "SELECT * FROM company";
	private static final String SQL_SELECT_ONE_COMPANY = "SELECT id,name FROM company WHERE id =";
	private static final String SQL_DELETE_COMPANY = "DELETE FROM company WHERE id= ?";
	private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id= ?";

	private static CompanyDAO instance = null;

	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
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
	private CompanyDAO() {
	}

	public final static CompanyDAO getInstance() {
		if (CompanyDAO.instance == null) {
			instance = new CompanyDAO();
		}
		return instance;
	}

	public Company find(long id) throws SqlCommandeException {
		Company tmp = null;
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT_ONE_COMPANY);) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				tmp = new Company(resultSet.getInt("id"), resultSet.getString("name"));
			}
		} catch (SQLException e) {
			logger.error("impossible de trouver la compagnie recherchée "+e.getMessage());
			throw new SqlCommandeException(SQL_SELECT_ONE_COMPANY);

		}
		return tmp;
	}

	public ArrayList<Company> findAll(int limits, int offset) throws SqlCommandeException {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_PAGE);) {

			preparedStatement.setLong(1, limits);
			preparedStatement.setLong(2, offset);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				tmp = new Company(result.getInt("id"), result.getString("name"));
				company_list.add(tmp);
			}

		} catch (SQLException e) {
			logger.error("pagination impossible "+e.getMessage());
			throw new SqlCommandeException(SQL_PAGE);

		}
		return company_list;
	}

	public ArrayList<Company> findAll() throws SqlCommandeException {
		ArrayList<Company> company_list = new ArrayList<Company>();
		Company tmp = null;
		try (Connection connect = hikariDataSource.getConnection();
				PreparedStatement preparedStatement = connect.prepareStatement(SQL_SELECT)) {

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				tmp = new Company(result.getInt("id"), result.getString("name"));
				company_list.add(tmp);
			}

		} catch (SQLException e) {
			logger.error("liste compagnie impossible "+ e.getMessage());
			throw new SqlCommandeException(SQL_SELECT);

		}
		return company_list;
	}

	public boolean delete(int id) throws SqlCommandeException {
		try(Connection connect = hikariDataSource.getConnection()){
			try(PreparedStatement preparedStatement = connect.prepareStatement(SQL_DELETE_COMPANY);
					PreparedStatement preparedStatement2 = connect.prepareStatement(SQL_DELETE_COMPUTER)){
				
				connect.setAutoCommit(false);
				
				preparedStatement2.setInt(1, id);
				preparedStatement.setInt(1, id);
				
				preparedStatement2.executeUpdate();
				preparedStatement.executeUpdate();
				
				connect.commit();
			}catch(SQLException ex) {
				logger.error("Echec delete company"+ ex.getMessage());
				connect.rollback();
			}
		}catch(SQLException ex) {
			logger.error("Echec delete company "+ ex.getMessage());
			throw new SqlCommandeException(SQL_DELETE_COMPANY);

		}
		return false;
	}

}