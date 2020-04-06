package at.ac.tuwien;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import at.ac.tuwien.dto.CreateDatabaseContainerDto;
import at.ac.tuwien.dto.CreateTableDto;

@Component
public class SqlDdlConverter {

	private String CREATE_DB_STMT = "CREATE DATABASE %s;";
	private String CREATE_TABLE_STMT = "CREATE TABLE %s (%s);";
	private String DROP_STMT = "DROP TABLE IF EXISTS %s;";

	public String getSqlStmtForCreateDB(CreateDatabaseContainerDto dto) {
//		String dbName = dto.getName();
//		String result = String.format(CREATE_DB_STMT, dbName);
//		return result;
		return null;
	}

	public String getSqlStmtForCreateTable(CreateTableDto dto) {
		String tableName = dto.getTableName();
		String columnNameWithTyp = getColumnNameWithDataTyp(dto);
		String result = String.format(CREATE_TABLE_STMT, tableName, columnNameWithTyp);
		return result;
	}

	public String getSqlStmtForDropTable(String tableName) {
		String result = String.format(DROP_STMT, tableName);
		return result;
	}

	private String getColumnNameWithDataTyp(CreateTableDto dto) {
		return dto.getColumns().entrySet().stream().map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

}
