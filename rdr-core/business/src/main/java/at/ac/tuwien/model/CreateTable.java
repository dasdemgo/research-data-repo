package at.ac.tuwien.model;

import java.util.Map;

public class CreateTable {

	private String tableName;

	private Map<String, String> records;

	private Map<String, String> columns;

	private String primaryKey;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, String> getRecords() {
		return records;
	}

	public void setRecords(Map<String, String> records) {
		this.records = records;
	}

	public Map<String, String> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, String> columns) {
		this.columns = columns;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
