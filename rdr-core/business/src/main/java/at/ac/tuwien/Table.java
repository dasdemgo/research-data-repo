package at.ac.tuwien;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Table {

	private String Table_Name;
	private Map<String, String> Values;

}
