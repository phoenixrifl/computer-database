package persistence.rowMappeur;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import modele.Company;

public class CompanyRowMappeur implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Company(rs.getLong("id"), rs.getString("name"));
	}

}
