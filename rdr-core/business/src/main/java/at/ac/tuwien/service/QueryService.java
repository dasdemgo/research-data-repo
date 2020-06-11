package at.ac.tuwien.service;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;
import at.ac.tuwien.querystore.service.QueryStoreService;

@Service
public class QueryService {

	@Autowired
	private DataStoreDaoImpl impl;

	@Autowired
	private QueryStoreService service;

	public void getResultSetOfQuery(ExecuteQueryDto dto) {
		// store query in query store
		ResultSet rs = impl.executeQuery(dto.getQuery());
//		service.createNewQuery(dto.getQuery());
		service.storeQuery(dto.getQuery(), rs);
	}

}
