package at.ac.tuwien.service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QueryRestService {


	private QueryService service;

	@Autowired
	public QueryRestService(QueryService service){
		this.service = service;
	}

	@PostMapping(value = "/getResultOfQuery")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "getting result of query")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Result of query") })
	public List<Map<String, Object>> getResultOfQuery(@RequestBody ExecuteQueryDto dto) {
		service.getResultOfQuery(dto);
		ObjectMapper objectMapper = new ObjectMapper();
		String response = null;
		try {
			response = objectMapper.writeValueAsString(dto.getResult());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return dto.getResult();
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
