package at.ac.tuwien.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.dto.CreateDatabaseContainerDto;

@RestController
@RequestMapping("/api")
public class DatabaseRestService {

	@Autowired
	private DatabaseService service;

	@PostMapping(value = "/createDatabaseContainer")
	public Response createDatabaseContainer(@RequestBody CreateDatabaseContainerDto dto) {
		service.create(dto);
		return Response.ok().build();
	}

	@GetMapping(value = "/getDatabaseContainers")
	public List<String> getDatabaseContainers() {
		return service.getDatabases();
	}

}
