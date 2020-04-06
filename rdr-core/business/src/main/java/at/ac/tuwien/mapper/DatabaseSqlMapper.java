package at.ac.tuwien.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.SqlDdlConverter;
import at.ac.tuwien.dto.CreateDatabaseContainerDto;

public class DatabaseSqlMapper {
	@Autowired
	private SqlDdlConverter ddlConverter;

	public String fromCreateDto(CreateDatabaseContainerDto dto) {
		return ddlConverter.getSqlStmtForCreateDB(dto);
	}

}
