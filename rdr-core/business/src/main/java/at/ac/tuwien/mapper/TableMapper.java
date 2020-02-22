package at.ac.tuwien.mapper;

import org.springframework.stereotype.Service;

import at.ac.tuwien.dto.TableDto;
import at.ac.tuwien.model.Table;

@Service
public class TableMapper {

	public Table toEntity(TableDto dto) {
		Table table = new Table();
		table.setTableName(dto.getTableName());
		table.setColumns(dto.getColumns());
		table.setData(dto.getData());
		return table;
	}

}
