package at.ac.tuwien;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.dto.InsertTableDto;

public class SqlDmlConverterTest {
	@Autowired
	private SqlDmlConverter converter;

	@Test
	public void testGetSqlStmtForInsertMultipleRows() throws Exception {
		InsertTableDto dto = new InsertTableDto();
		dto.setTableName("Persons");
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
		map.put("ID", "1");
		map.put("First_Name", "Shikhar");
		map.put("Last_Name", "Dhawan");
		map.put("Date_Of_Birth", "1981-12-05");

		map2.put("ID", "2");
		map2.put("First_Name", "Peter");
		map2.put("Last_Name", "Mustermann");
		map2.put("Date_Of_Birth", "1981-10-05");

		ArrayList<Map<String, String>> records = new ArrayList<>();
		records.add(map);
		records.add(map2);
		dto.setRecords(records);
		String insert = converter.getSqlStmtForInsertMultipleRows(dto);

	}
}
