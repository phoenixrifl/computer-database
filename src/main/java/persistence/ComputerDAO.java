package persistence;

import java.util.ArrayList;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import modele.Computer;
import persistence.rowMappeur.ComputerRowMappeur;
import servlet.model.Pagination;

@Component
public class ComputerDAO {

	private static final String SQL_INSERT = "INSERT INTO computer (id,name,introduced,discontinued,company_id) "
			+ "VALUES (NULL,:name,:introduced,:discontinued,:company_id);";
	private static final String SQL_DELETE = "DELETE FROM computer WHERE id= :id";
	private static final String SQL_SELECT_ONE = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id= :id";
	private static final String SQL_UPDATE = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:discontinued, company_id=:company_id WHERE id =:id";
	private static final String SQL_PAGE = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON computer.company_id = company.id ORDER BY ";
	private static final String SQL_SELECT = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id ";
	private static final String SQL_COUNT = "SELECT COUNT(*) AS total FROM computer";
	private static final String SQL_SEARCH = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, company.id, company.name "
			+ "FROM computer LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE computer.name LIKE :computerName OR company.name LIKE :companyName ORDER BY ";
	private static final String SQL_COUNT_SEARCH = "SELECT COUNT(*) AS total FROM computer LEFT JOIN company ON computer.company_id = company.id "
			+ "WHERE UPPER(computer.name) LIKE UPPER(:computerName) OR  UPPER(company.name) LIKE UPPER(:companyName) ";

	private static final String SQL_LIMIT_OFFSET = " LIMIT :limit OFFSET :offset";

	private DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;

	public ComputerDAO(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public boolean create(Computer obj) throws DataAccessException {

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("name", obj.getName());
		mapSqlParameterSource.addValue("introduced", obj.getIntroduced());
		mapSqlParameterSource.addValue("discontinued", obj.getDiscontinued());

		Integer company_id = (Objects.isNull(obj.getCompany())) ? null : obj.getCompany().getId_();
		mapSqlParameterSource.addValue("company_id", company_id);
		return (jdbcTemplate.update(SQL_INSERT, mapSqlParameterSource) == 1) ? true : false;

	}

	public boolean delete(Computer obj) throws DataAccessException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", obj.getId_());
		return (jdbcTemplate.update(SQL_DELETE, mapSqlParameterSource) == 1) ? true : false;

	}

	public boolean update(Computer obj) throws DataAccessException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", obj.getId_());
		mapSqlParameterSource.addValue("name", obj.getName());
		mapSqlParameterSource.addValue("introduced", obj.getIntroduced());
		mapSqlParameterSource.addValue("discontinued", obj.getDiscontinued());
		Integer company_id = (Objects.isNull(obj.getCompany()) || obj.getCompany().getId_() == -1) ? null
				: obj.getCompany().getId_();
		mapSqlParameterSource.addValue("company_id", company_id);
		return (jdbcTemplate.update(SQL_UPDATE, mapSqlParameterSource) == 1) ? true : false;
	}

	public Computer find(int id) throws DataAccessException {
		Computer computer = null;
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		ComputerRowMappeur computerRowMappeur = new ComputerRowMappeur();
		computer = jdbcTemplate.queryForObject(SQL_SELECT_ONE, mapSqlParameterSource, computerRowMappeur);
		return computer;
	}

	public int count() throws DataAccessException {
		int computerMax = 0;
		JdbcTemplate jdbcTemplate1 = new JdbcTemplate(dataSource);
		computerMax = jdbcTemplate1.queryForObject(SQL_COUNT, Integer.class);
		return computerMax;
	}

	public int countSearch(String search) throws DataAccessException {
		int computerSearchMax = 0;
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("computerName", "%" + search + "%");
		mapSqlParameterSource.addValue("companyName", "%" + search + "%");

		computerSearchMax = jdbcTemplate.queryForObject(SQL_COUNT_SEARCH, mapSqlParameterSource, Integer.class);
		return computerSearchMax;
	}

	public ArrayList<Computer> findAll() throws DataAccessException {
		ComputerRowMappeur computerRowMappeur = new ComputerRowMappeur();
		ArrayList<Computer> computers = (ArrayList<Computer>) jdbcTemplate.query(SQL_SELECT, computerRowMappeur);
		return computers;
	}

	public ArrayList<Computer> findAll(Pagination pagination) throws DataAccessException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("limit", pagination.getLimit());
		mapSqlParameterSource.addValue("offset", pagination.getOffset());
		ComputerRowMappeur computerRowMappeur = new ComputerRowMappeur();
		ArrayList<Computer> computers = (ArrayList<Computer>) jdbcTemplate.query(SQL_PAGE
				+ pagination.getByColumn().toString() + " " + pagination.getByMode().toString() + SQL_LIMIT_OFFSET,
				mapSqlParameterSource, computerRowMappeur);

		return computers;
	}

	public ArrayList<Computer> searchComputers(String search, Pagination pagination) throws DataAccessException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("computerName", "%" + search + "%");
		mapSqlParameterSource.addValue("companyName", "%" + search + "%");
		mapSqlParameterSource.addValue("limit", pagination.getLimit());
		mapSqlParameterSource.addValue("offset", pagination.getOffset());
		ComputerRowMappeur computerRowMappeur = new ComputerRowMappeur();

		ArrayList<Computer> computers = (ArrayList<Computer>) jdbcTemplate.query(SQL_SEARCH
				+ pagination.getByColumn().toString() + " " + pagination.getByMode().toString() + SQL_LIMIT_OFFSET,
				mapSqlParameterSource, computerRowMappeur);

		return computers;
	}

}
