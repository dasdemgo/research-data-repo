package at.ac.tuwien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.api.dto.CreateTableDto;
import at.ac.tuwien.persistence.impl.DataStoreDaoImpl;

@Component
public class HistoryTableGenerator {
	@Autowired
	private DataStoreDaoImpl impl;

	private String SYS_PERIOD_COLUMN_STMT = "ALTER TABLE %s ADD COLUMN sys_period tstzrange NOT NULL;";
	private String CREATE_TABLE_HISTORY_STMT = "CREATE TABLE %s_history (LIKE %s);";
	private String CREATE_VERSIONING_TRIGGER_STMT = "CREATE TRIGGER versioning_trigger BEFORE INSERT OR UPDATE OR DELETE ON %s FOR EACH ROW EXECUTE PROCEDURE versioning('sys_period', '%s_history', true);";

	public void generate(CreateTableDto dto) {
		impl.execute(getSqlStmtForCreateTableHistory(dto));
		impl.execute(getSqlStmtForSysPeriodColumn(dto));
		impl.execute(getSqlStmtForVersioningTrigger(dto));
	}

	private String getSqlStmtForCreateTableHistory(CreateTableDto dto) {
		String result = String.format(CREATE_TABLE_HISTORY_STMT, dto.getTableName(), dto.getTableName());
		return result;
	}

	private String getSqlStmtForSysPeriodColumn(CreateTableDto dto) {
		String result = String.format(SYS_PERIOD_COLUMN_STMT, dto.getTableName());
		return result;
	}

	private String getSqlStmtForVersioningTrigger(CreateTableDto dto) {
		String result = String.format(CREATE_VERSIONING_TRIGGER_STMT, dto.getTableName(), dto.getTableName());
		return result;
	}

}
