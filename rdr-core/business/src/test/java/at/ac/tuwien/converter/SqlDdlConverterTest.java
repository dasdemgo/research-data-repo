package at.ac.tuwien.converter;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.ac.tuwien.converter.SqlDdlConverter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SqlDdlConverter.class)
public class SqlDdlConverterTest {

	@Autowired
	private SqlDdlConverter builder;

	private static final String CREATE_SQL = "CREATE TABLE Persons (FirstName varchar(255), LastName varchar(255), Address varchar(255), City varchar(255))";

//	@Test
//	public void testGetSqlStmtForCreateTable() throws Exception {
//		CreateTableDto dto = new CreateTableDto();
//		dto.setTableName("Persons");
//		LinkedHashMap<String, String> map = new LinkedHashMap<>();
//		map.put("FirstName", "varchar(255)");
//		map.put("LastName", "varchar(255)");
//		map.put("Address", "varchar(255)");
//		map.put("City", "varchar(255)");
//		dto.setColumns(map);
//
//		assertThat(builder.getSqlStmtForCreateTable(dto), Matchers.is(CREATE_SQL));
//	}

}
