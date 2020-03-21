package at.ac.tuwien.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.InsertTableDto;
import at.ac.tuwien.mapper.TableSqlMapper;
import at.ac.tuwien.model.CreateTable;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

@Service
public class DataStoreService {

	@Autowired
	private TableSqlMapper mapper;

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

	public List<String> getTableNamesForDB(String dbName) {
		List<String> tableNames = new ArrayList<String>();
		Optional<ResultSet> rs = impl.executeQuery(mapper.fromTablesForDB(), dbName);
		if (rs.isPresent()) {
			try {
				while (rs.get().next()) {
					tableNames.add(rs.get().getString("table_name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tableNames;
	}

	public List<Map<String, Object>> getTable(String tableName, String dbName) {
		List<Map<String, Object>> records = null;
		Optional<ResultSet> rs = impl.executeQuery(mapper.fromTables(tableName), dbName);
		if (rs.isPresent()) {
			try {
				records = resultSetToList(rs.get());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return records;
	}

	private List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
			rows.add(row);
		}
		return rows;
	}

}
