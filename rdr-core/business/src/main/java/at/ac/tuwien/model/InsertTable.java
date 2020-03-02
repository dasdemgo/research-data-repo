package at.ac.tuwien.model;

import java.util.List;
import java.util.Map;

public class InsertTable {

	private String tableName;

	private List<Map<String, String>> records;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Map<String, String>> getRecords() {
		return records;
	}

	public void setRecords(List<Map<String, String>> records) {
		this.records = records;
	}

}
