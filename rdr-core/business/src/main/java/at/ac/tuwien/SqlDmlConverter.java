package at.ac.tuwien;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.dto.InsertTableDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

public class SqlDmlConverter {
	@Autowired
	private DataStoreDaoImpl impl;

	private String INSERT_STMT = "INSERT INTO %s (%s) VALUES %s;";

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
		Map<String, String> columnsMap = impl.getMetaDataForTable(dto.getTableName());

		return columnsMap.entrySet().stream()
				.filter(dataType -> dataType.getValue().contains("char") || dataType.getValue().contains("date"))
				.map(columnName -> columnName.getKey()).collect(Collectors.toList());
	}

}
