package at.ac.tuwien.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.SqlDdlConverter;
import at.ac.tuwien.dto.CreateDatabaseDto;

public class DatabaseSqlMapper {
	@Autowired
	private SqlDdlConverter ddlConverter;

	public String fromCreateDto(CreateDatabaseDto dto) {
		return ddlConverter.getSqlStmtForCreateDB(dto);
	}

}
