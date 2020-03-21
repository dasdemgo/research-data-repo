package at.ac.tuwien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.dto.CreateDatabaseDto;

@RestController
@RequestMapping("/api")
public class DatabaseRestService {

	@Autowired
	private DatabaseService service;

	@PostMapping(value = "/createDatabase")
	public void createDatabase(@RequestBody CreateDatabaseDto dto) {
		service.create(dto);
	}

	@GetMapping(value = "/getDatabases")
	public List<String> getDatabases() {
		return service.getDatabases();
	}

}
