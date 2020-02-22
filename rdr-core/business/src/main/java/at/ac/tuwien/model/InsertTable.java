package at.ac.tuwien.model;

import java.util.Map;

public class InsertTable {

	private String tableName;

	private Map<String, String> records;

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

}
