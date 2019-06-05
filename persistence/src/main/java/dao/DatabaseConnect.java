package dao;

import java.util.ResourceBundle;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnect {

	private static HikariConfig config;
	private static HikariDataSource dataSource;

	static {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		config = new HikariConfig();
		config.setDriverClassName(bundle.getString("driverClassName"));
		config.setJdbcUrl(bundle.getString("jdbcUrl"));
		config.setUsername(bundle.getString("username"));
		config.setPassword(bundle.getString("password"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		dataSource = new HikariDataSource(config);
	}

	public HikariDataSource getDataSource() {
		return dataSource;
	}
}
