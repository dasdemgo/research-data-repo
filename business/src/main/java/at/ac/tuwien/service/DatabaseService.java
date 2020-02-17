package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.DatabaseDto;
import at.ac.tuwien.mapper.DatabaseMapper;
import at.ac.tuwien.persistence.impl.DatabaseDaoImpl;

@Service
public class DatabaseService {
	@Autowired
	private DatabaseDaoImpl databaseDaoImpl;

	@Autowired
	private DatabaseMapper mapper;

	public void create(DatabaseDto databaseDto) {
		databaseDaoImpl.createDatabase(mapper.toEntity(databaseDto));
	}

}
