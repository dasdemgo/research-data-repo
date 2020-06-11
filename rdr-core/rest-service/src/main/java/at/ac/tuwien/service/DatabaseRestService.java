package at.ac.tuwien.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dockerjava.api.model.Container;

import at.ac.tuwien.api.dto.CreateDatabaseContainerDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class DatabaseRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseRestService.class);

	@Autowired
	private DatabaseService service;

	@PostMapping(value = "/databaseContainers")
	@ApiOperation(value = "creating a new database container")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "database container created") })
	public Response createDatabaseContainer(@RequestBody CreateDatabaseContainerDto dto) {
		LOGGER.debug("creating new database container");
		service.createDatabaseContainer(dto);
		return Response.status(Status.CREATED).build();
	}

	@GetMapping(value = "/databaseContainers")
	@ApiOperation(value = "getting database containers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "List of database containers") })
	public List<Container> getDatabaseContainers() {
		LOGGER.debug("get database containers");
		return service.getDatabaseContainers();
	}

}
