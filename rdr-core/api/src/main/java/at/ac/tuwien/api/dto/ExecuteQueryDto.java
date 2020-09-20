package at.ac.tuwien.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ExecuteQueryDto {

	private String query;

	private List<Map<String,Object>> result;

}
