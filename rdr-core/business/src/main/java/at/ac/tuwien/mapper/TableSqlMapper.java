package at.ac.tuwien.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.api.dto.CreateTableDto;
import at.ac.tuwien.api.dto.InsertTableDto;
import at.ac.tuwien.utils.SqlDdlConverter;
import at.ac.tuwien.utils.SqlDmlConverter;

@Component
public class TableSqlMapper {

	@Autowired
	private SqlDdlConverter ddlConverter;
	@Autowired
	private SqlDmlConverter dmlConverter;

	public String fromCreateDto(CreateTableDto dto) {
		return ddlConverter.convertCreateTableDtoToSql(dto);
	}

	public String fromDrop(String tableName) {
		return ddlConverter.getSqlStmtForDropTable(tableName);
	}

	public String fromInsertDto(InsertTableDto dto) {
		return dmlConverter.convertInsertTableDtoToSql(dto);
	}

	public String fromTablesForDB() {
		return dmlConverter.getSqlStmtForGetTablesForDb();
	}

	public String fromTables(String tableName) {
		return dmlConverter.getSqlStmtForGetTables(tableName);
	}

}
