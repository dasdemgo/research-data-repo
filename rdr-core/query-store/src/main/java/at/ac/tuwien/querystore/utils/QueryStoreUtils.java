package at.ac.tuwien.querystore.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryStoreUtils {

	public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
		List<Map<String, Object>> rows = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();

		while (rs.next()) {
			// Represent a row in DB. Key: Column name, Value: Column value
			Map<String, Object> row = new HashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				// Note that the index is 1-based
				String colName = rsmd.getColumnName(i);
				Object colVal = rs.getObject(i);
				row.put(colName, colVal);
			}
			rows.add(row);
		}
		return rows;
	}
}
