package at.ac.tuwien.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.config.UserConfig;
import at.ac.tuwien.persistence.DataStoreDao;

@Repository
public class DataStoreDaoImpl implements DataStoreDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataStoreDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void executeQuery(String query) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource("goekhanDB"));

		try {
			jdbcTemplate.execute(query);
		} catch (DataAccessException e) {
			LOGGER.error("There is a Problem with execute: " + e.getMessage());
		}
	}

	public Map<String, String> getMetaDataForTable(String tableName) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource("TestDB"));
		Map<String, String> columnsMap = new HashMap<String, String>();
		ResultSet resultSet = null;
		try {
			resultSet = jdbcTemplate.getDataSource().getConnection().getMetaData().getColumns(null, null, tableName,
					"%");
			while (resultSet.next()) {
				columnsMap.put(resultSet.getString("COLUMN_NAME"), resultSet.getString("TYPE_NAME"));
			}

		} catch (SQLException e) {
			LOGGER.error("There is a Problem with getting Metadata: " + e.getMessage());
		}
		return columnsMap;
	}

	public Optional<ResultSet> executeQuery(String fromTablesForDBQuery, String dbName) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource(dbName));
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = jdbcTemplate.getDataSource().getConnection().createStatement();
			rs = stmt.executeQuery(fromTablesForDBQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(rs);
	}

}
