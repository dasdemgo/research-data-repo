package at.ac.tuwien.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.api.dto.CreateTableDto;
import at.ac.tuwien.api.dto.InsertTableDto;
import at.ac.tuwien.mapper.TableSqlMapper;
import at.ac.tuwien.model.CreateTable;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;
import at.ac.tuwien.utils.HistoryTableGenerator;

@Service
public class DataStoreService {

	@Autowired
	private TableSqlMapper mapper;

	@Autowired
	private DataStoreDaoImpl impl;

	@Autowired
	private HistoryTableGenerator historyTableGen;

	public CreateTable createTable(CreateTableDto dto) {
		impl.execute(mapper.fromCreateDto(dto));
		historyTableGen.generate(dto);
		return null;
	}

	public InsertTableDto insert(InsertTableDto dto) {
		impl.execute(mapper.fromInsertDto(dto));

		return null;
	}

	public void drop(String tableName) {
		impl.execute(mapper.fromDrop(tableName));
	}

	public List<Map<String, Object>> getTable(String tableName) {
//		List<Map<String, Object>> records = null;
//		List rs = impl.executeQuery(mapper.fromTables(tableName));
//		try {
//			records = resultSetToList(rs);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return records;
		return null;
	}

}
