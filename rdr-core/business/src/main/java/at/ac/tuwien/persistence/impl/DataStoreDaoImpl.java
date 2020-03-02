package at.ac.tuwien.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.config.UserConfig;
import at.ac.tuwien.persistence.DataStoreDao;

@Repository
public class DataStoreDaoImpl implements DataStoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void execute(String query) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource("TestDB"));
		jdbcTemplate.execute(query);
	}

}
