package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.TableDto;
import at.ac.tuwien.mapper.TableMapper;
import at.ac.tuwien.model.Table;
import at.ac.tuwien.persistence.impl.TableDaoImpl;

@Service
public class TableService {

	@Autowired
	private TableDaoImpl tableDaoImpl;

	@Autowired
	private TableMapper mapper;

	public void create(TableDto tableDto) {
		tableDaoImpl.createTable(mapper.toEntity(tableDto));
	}

	public Table getTable(String id) {
		return null;
	}

}
