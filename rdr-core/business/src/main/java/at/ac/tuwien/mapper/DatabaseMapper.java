package at.ac.tuwien.mapper;

import org.springframework.stereotype.Component;

import at.ac.tuwien.dto.DatabaseDto;
import at.ac.tuwien.model.Database;

@Component
public class DatabaseMapper {

	public Database toEntity(DatabaseDto dto) {
		Database database = new Database();
		database.setName(dto.getName());
		return database;
	}

}
