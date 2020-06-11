package at.ac.tuwien.querystore.service;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.querystore.dao.impl.QueryStoreDaoImpl;
import at.ac.tuwien.querystore.model.Query;

@Service
public class QueryStoreService {

	@Autowired
	private QueryStoreDaoImpl impl;

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryStoreService.class);

	public void storeQuery(String sqlQuery, ResultSet rs) {
		if (checkIfResultSetExists(rs)) {
			return;
		}
		// sqlQuery ausführen, Resultset kommt zurück. Resultset hashen =
		// resultsetchecksum ->
		// Select * from query_store where query_store.sqlQuery And querystore.resultset
		// = resultsetchecksum
		// gibts es so eine Query? Wenn Ja, ist der Resultset ident?
		// ich bräuchte eine Zentrale Hashlogik für die Resultsets

		Query query = new Query();
		query.setQuery(sqlQuery);
		query.setQueryHash(calculateHash(sqlQuery));
		query.setTableName("Dummy table");
		query.setExecTimestamp(new Date());
		query.setResultsetHash(calculateHash(rs.toString()));
		impl.persistQuery(query);
	}

	public ResultSet getResultSetForPid(String pid) {
		return null;
	}

	private String calculateHash(String toBeHashed) {
		return DigestUtils.sha512Hex(toBeHashed);
	}

	private boolean checkIfResultSetExists(ResultSet rs) {
		String resultSetHash = calculateHash(rs.toString());
		Optional<Query> queryForResultSetHash = impl.getQueryForResultSetHash(resultSetHash);
		if (queryForResultSetHash.isPresent()) {
			LOGGER.info("query ist present in query_store with PID: " + queryForResultSetHash.get().getPid());
			return true;
		}
		return false;
	}

}
