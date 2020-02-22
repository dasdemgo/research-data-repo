package at.ac.tuwien.persistence.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.config.UserConfig;
import at.ac.tuwien.model.CreateTable;
import at.ac.tuwien.persistence.DataStoreDao;

@Repository
public class DataStoreDaoImpl implements DataStoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void createTable(CreateTable table) {
		jdbcTemplate.setDataSource(UserConfig.getDataSource("TestDB"));
		jdbcTemplate.execute("CREATE TABLE " + table.getTableName() + " (" + getColumnNameWithDataTyp(table) + ")");
	}

	public void insertTable(CreateTable table) {

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

	protected String getColumnNameWithDataTyp(CreateTable table) {
		return table.getRecords().entrySet().stream().map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

}
