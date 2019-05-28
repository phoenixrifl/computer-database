package persistence;

import java.util.ArrayList;
import java.util.Objects;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import exception.SqlCommandeException;
import modele.Company;
import persistence.rowMappeur.CompanyRowMappeur;
import servlet.model.Pagination;

@Component
public class CompanyDAO {

	private static final String SQL_PAGE = "SELECT * FROM company ORDER BY id LIMIT :limit OFFSET :offset";
	private static final String SQL_SELECT = "SELECT * FROM company";
	private static final String SQL_SELECT_ONE_COMPANY = "SELECT id,name FROM company WHERE id =:id";
	private static final String SQL_DELETE_COMPANY = "DELETE FROM company WHERE id= :id";

	private DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;

	public CompanyDAO(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Company find(long id) throws DataAccessException {
		Company company = null;
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		CompanyRowMappeur companyRowMappeur = new CompanyRowMappeur();
		company = jdbcTemplate.queryForObject(SQL_SELECT_ONE_COMPANY, mapSqlParameterSource, companyRowMappeur);
		return company;
	}

	public ArrayList<Company> findAll(Pagination pagination) throws SqlCommandeException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		CompanyRowMappeur companyRowMappeur = new CompanyRowMappeur();
		mapSqlParameterSource.addValue("limit", pagination.getLimit());
		mapSqlParameterSource.addValue("offset", pagination.getOffset());
		ArrayList<Company> company = (ArrayList<Company>) jdbcTemplate.query(SQL_PAGE, mapSqlParameterSource,
				companyRowMappeur);
		return company;
	}

	public ArrayList<Company> findAll() throws SqlCommandeException {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		CompanyRowMappeur companyRowMappeur = new CompanyRowMappeur();
		ArrayList<Company> company = (ArrayList<Company>) jdbcTemplate.query(SQL_SELECT, mapSqlParameterSource,
				companyRowMappeur);
		return company;
	}

	@Transactional
	public boolean delete(int id) throws SqlCommandeException {
		Company company = null;
		company = find(id);
		if (Objects.isNull(company))
			return false;

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);

		return (jdbcTemplate.update(SQL_DELETE_COMPANY, mapSqlParameterSource) != 0);
	}

}