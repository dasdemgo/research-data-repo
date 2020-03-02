package at.ac.tuwien;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.InsertTableDto;

@Component
public class SqlConverter {

	private String CREATE_STMT = "CREATE TABLE %s (%s);";
	private String INSERT_STMT = "INSERT INTO %s (%s) VALUES (%s);";

	public String getSqlStmtForCreateTable(CreateTableDto dto) {
		String tableName = dto.getTableName();
		String columnNameWithTyp = getColumnNameWithDataTyp(dto);

		CREATE_STMT = String.format(tableName, columnNameWithTyp);

		return CREATE_STMT;
	}

	public String getSqlStmtForInsertTable(InsertTableDto dto) {
		ArrayList<String> values = new ArrayList<String>();

		for (Map<String, String> record : dto.getRecords()) {
			values.add("(" + record.values().stream().collect(Collectors.joining(", ")) + ")");

		}
		String valuesResult = values.stream().collect(Collectors.joining(", "));
		String columnNames = dto.getRecords().get(0).keySet().stream().collect(Collectors.joining(","));

		INSERT_STMT = String.format(INSERT_STMT, dto.getTableName(), columnNames, valuesResult);
		return INSERT_STMT;
	}

	private String getColumnNameWithDataTyp(CreateTableDto dto) {
		return dto.getColumns().entrySet().stream().map(entry -> entry.getKey() + " " + entry.getValue())
				.collect(Collectors.joining(", "));
	}

	private String getRecordsfromDto(CreateTableDto dto) {
		String insert = "INSERT INTO %1$s %2$s %values(%3$s) VALUES (%4$s);";
		String.format(insert, getColumnsFromDto(dto));

		return null;
	}

	private String getValuesFromDto(CreateTableDto dto) {
		return null;
	}

	private String getColumnsFromDto(CreateTableDto dto) {
		return null;
	}

//	// Reads a file and converts it to JSON
//	public static JSONObject getJSONfromFile(String fileName) throws IOException {
//
//		BufferedReader br = null;
//		String sTotalLine = "";
//
//		try {
//			String sCurrentLine;
//
//			br = new BufferedReader(new FileReader(fileName));
//
//			while ((sCurrentLine = br.readLine()) != null) {
//				sTotalLine = sTotalLine + sCurrentLine.trim();
//			}
//
//		} catch (IOException e) {
//			System.out.println("File failed to load.");
//			sTotalLine = "{}";
//
//		} finally {
//			try {
//				if (br != null)
//					br.close();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return new JSONObject(sTotalLine);
//	}

//	
//
//	Gson gson = new Gson();
//	JsonObject asJsonObject = new JsonParser()
//			.parse(new FileReader("C:\\Bachelorprojekt\\Development\\my-players.json")).getAsJsonObject();
//
//	String tableNameFromJson = asJsonObject.get("Table_Name").toString();
//	JsonArray tableValuesFromJson = asJsonObject.get("Values").getAsJsonArray();
//	String columnNames = tableValuesFromJson.get(0).getAsJsonObject().keySet().toString();

//
//	for (JsonElement elem : tableValuesFromJson) {
//		insertValues += gson.fromJson(elem, Map.class).values().toString() + ",";
//	}
//	columnNames = StringUtils.replaceChars(columnNames, ']', ')');
//	columnNames = StringUtils.replaceChars(columnNames, '[', '(');
//	insertValues = StringUtils.replaceChars(insertValues, '[', '(');
//	insertValues = StringUtils.replaceChars(insertValues, ']', ')');
//	insert = String.format(insert, tableNameFromJson, columnNames, insertValues);

}
