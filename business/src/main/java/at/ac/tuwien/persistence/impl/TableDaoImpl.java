package at.ac.tuwien.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import at.ac.tuwien.config.UserConfig;
import at.ac.tuwien.model.Table;
import at.ac.tuwien.persistence.TableDao;

@Component
public class TableDaoImpl implements TableDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createTable(Table table) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource("TestDB"));
		jdbcTemplate.execute("CREATE TABLE " + table.getTableName() + " (id int, name varchar)");
	}

	public void updateTable() {

	}

	public void deleteTable() {

	}

	public void readTable() {

	}
}
