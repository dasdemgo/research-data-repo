package at.ac.tuwien.service;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
import at.ac.tuwien.api.dto.TableDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;
import at.ac.tuwien.querystore.service.QueryStoreService;

@Service
public class QueryService {

	@Autowired
	private DataStoreDaoImpl impl;

	@Autowired
	private QueryStoreService queryStoreService;

	public void getResultSetOfQuery(ExecuteQueryDto dto) {
		ResultSet rs = impl.executeQuery(dto.getQuery());

		queryStoreService.storeQuery(dto, rs);
	}

	public TableDto resolvePID(int pid) {
		return queryStoreService.resolvePID(pid);
	}

}
