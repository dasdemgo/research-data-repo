package at.ac.tuwien.api.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertTableDto {

	private String tableName;

	//private List<Map<String, String>> records;
private List<JSONObject> records;

}
