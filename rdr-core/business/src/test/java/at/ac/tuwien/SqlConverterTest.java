package at.ac.tuwien;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.InsertTableDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SqlConverter.class)
public class SqlConverterTest {

	@Autowired
	private SqlConverter builder;

	private static final String CREATE_SQL = "CREATE TABLE Persons (FirstName varchar(255), LastName varchar(255), Address varchar(255), City varchar(255))";

	@Test
	public void testGetSqlStmtForCreateTable() throws Exception {
		CreateTableDto dto = new CreateTableDto();
		dto.setTableName("Persons");
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("FirstName", "varchar(255)");
		map.put("LastName", "varchar(255)");
		map.put("Address", "varchar(255)");
		map.put("City", "varchar(255)");
		dto.setColumns(map);

		assertThat(builder.getSqlStmtForCreateTable(dto), Matchers.is(CREATE_SQL));
	}

	@Test
	public void testGetSqlStmtForInsertTable() throws Exception {
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
		String insert = builder.getSqlStmtForInsertTable(dto);

	}

}
