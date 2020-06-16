package at.ac.tuwien.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.api.dto.InsertTableDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

@Component
public class SqlDmlConverter {
	@Autowired
	private DataStoreDaoImpl impl;

	private String INSERT_STMT = "INSERT INTO %s (%s) VALUES %s;";
	private String SELECT_ALL_TABLE_NAMES_FROM_DB = "SELECT table_name from information_schema.tables WHERE TABLE_SCHEMA = 'public';";
	private String SELECT_TABLE = "SELECT * from %s";
	private String SELECT_COLUMNNAME_DATATYPE_STMT = "SELECT COLUMN_NAME, DATA_TYPE FROM information_schema.columns WHERE TABLE_NAME='%s';";

	public String getSqlStmtForInsertMultipleRows(InsertTableDto dto) {
		ArrayList<String> values = new ArrayList<String>();

		List<String> apostropheNeededColumns = getApostropheNeededColumns(dto);

		for (Map<String, String> record : dto.getRecords()) {

			record.entrySet().stream().filter(e -> checkIfTypeMatches(apostropheNeededColumns, e.getKey()))
					.forEach(elem -> record.replace(elem.getKey(), "'" + elem.getValue() + "'"));

			values.add("(" + record.entrySet().stream().map(s -> s.getValue()).collect(Collectors.joining(", ")) + ")");
		}

		String valuesResult = values.stream().collect(Collectors.joining(", "));
		String columnNames = dto.getRecords().get(0).keySet().stream().collect(Collectors.joining(","));

		String result = String.format(INSERT_STMT, dto.getTableName(), columnNames, valuesResult);
		return result;
	}

	private boolean checkIfTypeMatches(List<String> columns, String type) {
		return columns.stream().filter(columnName -> StringUtils.containsIgnoreCase(columnName, type)).findFirst()
				.isPresent();
	}

	private List<String> getApostropheNeededColumns(InsertTableDto dto) {
		Map<String, String> columnsMap = impl.getColumnNamesAndColumnTypes(dto.getTableName());
		return columnsMap.entrySet().stream()
				.filter(dataType -> dataType.getValue().contains("char") || dataType.getValue().contains("date"))
				.map(columnName -> columnName.getKey()).collect(Collectors.toList());
	}

	public String getSqlStmtForGetTablesForDb() {
		return SELECT_ALL_TABLE_NAMES_FROM_DB;
	}

	public String getSqlStmtForGetTables(String tableName) {
		String result = String.format(SELECT_TABLE, tableName);
		return result;
	}

}
