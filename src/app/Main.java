package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		final Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("db.properties");
			prop.load(input);
			System.out.println(prop.getProperty("db.url"));
			
		}catch(final IOException ex) {
			ex.printStackTrace();
		}
	}

}
