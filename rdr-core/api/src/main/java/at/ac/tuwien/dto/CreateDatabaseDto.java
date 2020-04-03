package at.ac.tuwien.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDatabaseDto {

	private String containerName;
	private String dbName;

}
