package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import persistence.ComputerDAO;

public class Main {

	public static void main(String[] args) {
		final Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("db.properties");
			prop.load(input);
			
			try {
				Connection con = DriverManager.getConnection(
						prop.getProperty("db.url"),
						prop.getProperty("db.username"),
						prop.getProperty("db.password"));
			ComputerDAO comp = new ComputerDAO(con);
			System.out.println(comp.find(5));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}catch(final IOException ex) {
			ex.printStackTrace();
		}
	}

}
