package vn.edu.uit.realestate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Model.GraphRealEstateKind;
import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;
import vn.edu.uit.realestate.Graph.Model.GraphUser;
import vn.edu.uit.realestate.Service.InitGraphDatabaseService;

@RestController
public class InitGraphDatabaseController {

	@Autowired 
	private InitGraphDatabaseService initGraphDatabaseService;
	
	@RequestMapping("/graph/init")
	public ResponseEntity<Iterable<GraphUser>> initDatabase() {
		initGraphDatabaseService.deleteEntireNeo4j();
		initGraphDatabaseService.saveTradeKindToNeo4j();
		initGraphDatabaseService.saveRealEstateKindToNeo4j();
		return new ResponseEntity<>(initGraphDatabaseService.saveUserToNeo4j(), HttpStatus.OK);
	}
}
