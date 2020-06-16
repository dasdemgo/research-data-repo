package at.ac.tuwien.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExecuteQueryDto {

	private int pid;

	private String tableName;

	private String query;

}
