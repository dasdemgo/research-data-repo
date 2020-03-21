package at.ac.tuwien.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.config.UserConfig;
import at.ac.tuwien.persistence.DatabaseDao;

@Repository
public class DatabaseDaoImpl implements DatabaseDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void executeQuery(String query) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource(""));

		try {
			jdbcTemplate.execute(query);
		} catch (DataAccessException e) {
			LOGGER.error("There is a Problem with execute: " + e.getMessage());
		}
	}

	public List<String> getDatabases() {
		jdbcTemplate.setDataSource(UserConfig.getDataSource(""));
		Statement stmt;
		ResultSet rs;
		List<String> databases = new ArrayList<String>();
		try {
			stmt = jdbcTemplate.getDataSource().getConnection().createStatement();
			rs = stmt.executeQuery("SELECT datname FROM pg_database;");
			while (rs.next()) {
				databases.add(rs.getString("datname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return databases;
	}

}
