package at.ac.tuwien.querystore.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
import at.ac.tuwien.api.dto.TableDto;
import at.ac.tuwien.querystore.dao.impl.QueryStoreDaoImpl;
import at.ac.tuwien.querystore.model.Query;
import at.ac.tuwien.querystore.utils.HistoryTableWrapper;
import at.ac.tuwien.querystore.utils.QueryStoreUtils;

@Service
public class QueryStoreService {

	@Autowired
	private QueryStoreDaoImpl impl;

	private static final Logger LOGGER = LoggerFactory.getLogger(QueryStoreService.class);

	public void storeQuery(ExecuteQueryDto queryDto, ResultSet rs) {
		if (checkIfResultSetExists(rs)) {
			return;
		}

		Query query = new Query();
		query.setQuery(queryDto.getQuery());
		query.setQueryHash(calculateHash(queryDto.getQuery()));
		query.setTableName(queryDto.getTableName());
		query.setExecTimestamp(new Date());
		query.setResultsetHash(calculateHash(rs.toString()));
		impl.persistQuery(query);
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

	public TableDto resolvePID(int pid) {
		TableDto dto = new TableDto();
		Optional<Query> queryForPID = impl.getQueryForPID(pid);
		if (!queryForPID.isPresent()) {
			return null;
		}
		try {
			ResultSet resultSet = impl.executeQuery(queryForPID.get().getQuery());
			if (checkIfResultSetHashIsEqual(resultSet, queryForPID.get())) {
				dto.setResult(QueryStoreUtils.resultSetToList(resultSet));
				return dto;
			}
			resultSet = impl.executeQuery(new HistoryTableWrapper().determineSqlStmtForHistoryData(queryForPID.get()));
			dto.setResult(QueryStoreUtils.resultSetToList(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	private boolean checkIfResultSetHashIsEqual(ResultSet rs, Query query) {
		String hashedResultSet = DigestUtils.sha512Hex(rs.toString());
		return hashedResultSet.equals(query.getResultsetHash());
	}

}
