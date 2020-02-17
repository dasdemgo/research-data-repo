package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.dto.DatabaseDto;

@RestController
public class DatabaseRestService {

	@Autowired
	private DatabaseService service;

	@PostMapping(value = "/createDatabase")
	public void createDatabase(@RequestBody DatabaseDto dto) {
		service.create(dto);
	}

}
