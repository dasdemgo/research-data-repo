package at.ac.tuwien.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import at.ac.tuwien.model.Database;
import at.ac.tuwien.persistence.DatabaseDao;

@Component
public class DatabaseDaoImpl implements DatabaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createDatabase(Database database) {
		jdbcTemplate.execute("CREATE DATABASE " + database.getName());
	}

}
