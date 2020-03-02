package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.dto.CreateTableDto;
import at.ac.tuwien.dto.DeleteRecordDto;
import at.ac.tuwien.dto.InsertTableDto;
import at.ac.tuwien.dto.UpdateTableDto;

@RestController
@RequestMapping("/api")
public class TableRestService {

	@Autowired
	private DataStoreService service;

	@PostMapping(value = "/createTable")
	public ResponseEntity<String> createTable(@RequestBody CreateTableDto dto) {
		return ResponseEntity.ok(service.create(dto).getTableName());
	}

	@PostMapping(value = "/insertTable")
	public ResponseEntity<String> insertTable(@RequestBody InsertTableDto dto) {
		return ResponseEntity.ok(service.insert(dto).getTableName());
	}

	@PutMapping(value = "/updateTable")
	public ResponseEntity<String> updateTable(@RequestBody UpdateTableDto dto) {
		// TODO
		return null;
	}

	@DeleteMapping(value = "/dropTable")
	public ResponseEntity<String> dropTable(@RequestParam(name = "tableName") String tableName) {
		return null;
	}

	@DeleteMapping(value = "/deleteRecord")
	public ResponseEntity<String> deleteRecord(@RequestBody DeleteRecordDto dto) {
		return null;
	}

//	@GetMapping(value = "/getTables")
//	public String helloWorld() {
//		public ResponseEntity<String> getTables() {
//			return ResponseEntity.ok(service.create(dto).getTableName());
//		}
//	}

}
