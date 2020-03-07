package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.InsertTableDto;
import at.ac.tuwien.mapper.TableToSqlMapper;
import at.ac.tuwien.model.CreateTable;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

@Service
public class DataStoreService {

	@Autowired
	private TableToSqlMapper mapper;

	@Autowired
	private DataStoreDaoImpl impl;

	public CreateTable create(CreateTableDto dto) {
		impl.executeQuery(mapper.fromCreateDto(dto));
		return null;
	}

	public InsertTableDto insert(InsertTableDto dto) {
		impl.executeQuery(mapper.fromInsertDto(dto));

		return null;
	}

	public void drop(String tableName) {
		impl.executeQuery(mapper.fromDrop(tableName));
	}

}
