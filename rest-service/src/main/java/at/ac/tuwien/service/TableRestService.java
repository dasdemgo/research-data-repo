package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.dto.TableDto;

@RestController
public class TableRestService {

	@Autowired
	private TableService service;

	@PostMapping(value = "/createTable")
	public void createTable(@RequestBody TableDto dto) {
		service.create(dto);
	}

	@GetMapping(value = "/")
	public String helloWorld() {
		return "Hello World";
	}

}
