package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.DatabaseDto;
import at.ac.tuwien.persistence.impl.DatabaseDaoImpl;

@Service
public class DatabaseService {
	@Autowired
	private DatabaseDaoImpl dao;

	public void create(DatabaseDto databaseDto) {
//		dao.createDatabase(mapper.toEntity(databaseDto));
	}

}
