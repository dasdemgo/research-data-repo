package at.ac.tuwien.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteRecordDto {

	private String tableName;
	private Long primaryKey;
}
