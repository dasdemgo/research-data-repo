package at.ac.tuwien.querystore.utils;

import org.apache.commons.lang.StringUtils;

import at.ac.tuwien.querystore.model.Query;

public class HistoryTableWrapper {

	public String determineSqlStmtForHistoryData(Query requestQuery) {
		String query = requestQuery.getQuery();
		if (StringUtils.containsIgnoreCase(query, "WHERE")) {
			query += " AND ";
		} else {
			query += " WHERE ";
		}

		return getBaseQueryWithTimeStampCheck(requestQuery, query) + " UNION "
				+ getHistoryQueryWithTimeStampCheck(requestQuery, query);
	}

	private String getHistoryQueryWithTimeStampCheck(Query requestQuery, String tempQuery) {
		tempQuery.replace(requestQuery.getTableName(), requestQuery.getTableName() + "_history");
		return tempQuery + " " + getTimeStampCheckPart(requestQuery);
	}

	private String getBaseQueryWithTimeStampCheck(Query requestQuery, String tempQuery) {
		return tempQuery + " " + getTimeStampCheckPart(requestQuery);
	}

	private String getTimeStampCheckPart(Query requestQuery) {
		return String.format("lower(sys_period) <= '%s' AND '%s' < upper(sys_period) OR upper(sys_period) IS NULL",
				requestQuery.getExecTimestamp(), requestQuery.getExecTimestamp());
	}
}
