package at.ac.tuwien.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
import at.ac.tuwien.api.dto.TableDto;
import at.ac.tuwien.service.validation.ValidationError;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class QueryRestService {

	@Autowired
	private QueryService service;

	@PostMapping(value = "/getResultSetOfQuery")
	@ApiOperation(value = "getting resultset of query")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultset of query") })
	public ResponseEntity<String> getResultSetOfQuery(@RequestBody ExecuteQueryDto dto) {
		service.getResultSetOfQuery(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/resolvePID")
	@ApiOperation(value = "getting resultset by pid")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Result by pid"),
			@ApiResponse(code = 404, message = "pid not found") })
	public Response resolvePID(@RequestParam(name = "pid") String pid) {
		TableDto resolvedPID = service.resolvePID(Integer.parseInt(pid));
		if (resolvedPID != null) {
			return Response.ok().entity(resolvedPID).build();
		}
		return createStatusNotFoundResponseForPid(pid);
	}

	protected Response createStatusNotFoundResponseForPid(String pid) {
		ValidationError error = new ValidationError();
		error.setMessage("There is no Result for pid: " + pid);
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
