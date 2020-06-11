package at.ac.tuwien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.api.dto.ExecuteQueryDto;
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

}
