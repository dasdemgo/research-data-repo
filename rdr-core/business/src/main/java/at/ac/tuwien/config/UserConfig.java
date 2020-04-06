package at.ac.tuwien.config;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class UserConfig {

	public static DataSource getDataSource(String dbName) {
		// Creates a new instance of DriverManagerDataSource and sets
		// the required parameters such as the Jdbc Driver class,
		// Jdbc URL, database user name and password.
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:8440/" + dbName);
		dataSource.setUsername("postgres");
		dataSource.setPassword("mysecretpassword");
		return dataSource;
	}
}
