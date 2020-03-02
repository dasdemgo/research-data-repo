package at.ac.tuwien.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.SqlConverter;
import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.InsertTableDto;

@Component
public class CreateTableToSqlMapper {

	@Autowired
	private SqlConverter sqlBuilder;

	public String fromCreateDto(CreateTableDto dto) {
		return sqlBuilder.getSqlStmtForCreateTable(dto);
	}

	public String fromInsertDto(InsertTableDto dto) {
		return sqlBuilder.getSqlStmtForInsertTable(dto);
	}

}
