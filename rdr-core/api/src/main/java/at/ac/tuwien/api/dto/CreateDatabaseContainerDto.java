package at.ac.tuwien.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDatabaseContainerDto {

	private String containerName;
	private String dbName;

}
