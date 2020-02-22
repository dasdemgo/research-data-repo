package at.ac.tuwien.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.model.CreateTable;

public class DataStoreDaoImplTest {

	@Autowired
	private DataStoreDaoImpl impl;

	@Test
	public void testGetColumnNameWithDataTyp() throws Exception {
		CreateTable table = new CreateTable();

		Map<String, String> columns = new HashMap<String, String>();

		columns.put("ID", "int");
		columns.put("Name", "VARCHAR (255)");
		columns.put("Name", "VARCHAR (255)");

		table.setRecords(columns);

		// assertThat(impl.getColumnNameWithDataTyp(table), is("ID int, Name VARCHAR
		// (255)"));

	}

}
