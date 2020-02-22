package at.ac.tuwien.persistence.impl;

import java.util.stream.Collectors;

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
		jdbcTemplate.execute("CREATE TABLE " + table.getTableName() + " (" + getColumnNameWithDataTyp(table) + ")");
	}

	public void insertTable(Table table) {

//		jdbcTemplate.execute("INSERT INTO " + table.getTableName() + "(...) VALUES " + "("   ")" );
	}

	public void updateTable() {

	}

	public void deleteTable() {

	}

	public void readTable() {
//		INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
//		VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway');
	}

	protected String getColumnNameWithDataTyp(Table table) {
		return table.getColumns().entrySet().stream().map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));
	}
}
