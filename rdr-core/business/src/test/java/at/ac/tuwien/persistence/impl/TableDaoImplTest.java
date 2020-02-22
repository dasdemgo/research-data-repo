package at.ac.tuwien.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.model.Table;

public class TableDaoImplTest {

	@Autowired
	private TableDaoImpl impl;

	@Test
	public void testGetColumnNameWithDataTyp() throws Exception {
		Table table = new Table();

		Map<String, String> columns = new HashMap<String, String>();

		columns.put("ID", "int");
		columns.put("Name", "VARCHAR (255)");
		columns.put("Name", "VARCHAR (255)");

		table.setColumns(columns);

//		assertThat(impl.getColumnNameWithDataTyp(table), is("ID int, Name VARCHAR (255)"));

	}

}
