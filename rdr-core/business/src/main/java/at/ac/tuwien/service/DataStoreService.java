package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.model.CreateTable;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

@Service
public class DataStoreService {

	@Autowired
	private DataStoreDaoImpl tableDaoImpl;

	public CreateTable create(CreateTableDto createTable) {
		// TODO build sql query
		// than delegate to DAO
		return null;
	}

	public CreateTable getTable(String id) {
		return null;
	}

}
