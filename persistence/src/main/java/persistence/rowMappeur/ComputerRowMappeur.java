package persistence.rowMappeur;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import model.Computer;

public class ComputerRowMappeur implements RowMapper<Computer> {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Computer(rs.getLong("computer.id"), rs.getString("computer.name"),
				(rs.getDate("introduced") != null) ? rs.getDate("introduced").toLocalDate() : null,
				(rs.getDate("discontinued") != null) ? rs.getDate("discontinued").toLocalDate() : null,
				rs.getLong("company_id"), rs.getString("company.name"));
	}

}
