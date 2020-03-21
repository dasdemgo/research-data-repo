package at.ac.tuwien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.CreateDatabaseDto;
import at.ac.tuwien.mapper.DatabaseSqlMapper;
import at.ac.tuwien.persistence.impl.DatabaseDaoImpl;

@Service
public class DatabaseService {
	@Autowired
	private DatabaseDaoImpl dao;

	private DatabaseSqlMapper mapper;

	public void create(CreateDatabaseDto databaseDto) {
		dao.executeQuery(mapper.fromCreateDto(databaseDto));
	}

	public List<String> getDatabases() {
		return dao.getDatabases();
	}

}
