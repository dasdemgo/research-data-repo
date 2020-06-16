package at.ac.tuwien.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.api.dto.CreateDatabaseContainerDto;
import at.ac.tuwien.utils.SqlDdlConverter;

public class DatabaseSqlMapper {
	@Autowired
	private SqlDdlConverter ddlConverter;

	public String fromCreateDto(CreateDatabaseContainerDto dto) {
		return ddlConverter.getSqlStmtForCreateDB(dto);
	}

}
