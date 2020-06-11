package at.ac.tuwien.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExecuteQueryDto {

	private String tableName;

	private String query;

}
