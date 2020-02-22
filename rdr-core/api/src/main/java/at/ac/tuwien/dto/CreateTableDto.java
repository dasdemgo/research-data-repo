package at.ac.tuwien.dto;

import java.util.Map;

public class CreateTableDto {
	private String primaryKey;

	private String tableName;

	private Map<String, String> columns;

	private Map<String, String> records;

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public Map<String, String> getRecords() {
		return records;
	}

	public void setRecords(Map<String, String> records) {
		this.records = records;
	}

}
