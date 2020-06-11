package at.ac.tuwien.api.dto;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTableDto {
	private String primaryKey;

	private String tableName;

	private Map<String, String> columns;

}
