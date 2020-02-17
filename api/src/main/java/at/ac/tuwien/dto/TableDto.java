package at.ac.tuwien.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDto {

	private String tableName;

//	private Map<String, String> columns;
//
//	private Map<String, String> data;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

//	public Map<String, String> getColumns() {
//		return columns;
//	}
//
//	public void setColumns(Map<String, String> columns) {
//		this.columns = columns;
//	}
//
//	public Map<String, String> getData() {
//		return data;
//	}
//
//	public void setData(Map<String, String> data) {
//		this.data = data;
//	} 
}
