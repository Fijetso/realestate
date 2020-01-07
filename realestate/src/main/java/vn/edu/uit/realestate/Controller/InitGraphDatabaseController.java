package vn.edu.uit.realestate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Service.InitGraphDatabaseService;

@RestController
public class InitGraphDatabaseController {

	@Autowired 
	private InitGraphDatabaseService initGraphDatabaseService;
	
	@RequestMapping(value= "/graph/init", method = RequestMethod.GET)
	public ResponseEntity<Iterable<GraphTrade>> initDatabase() {
		initGraphDatabaseService.deleteEntireNeo4j();
//		initGraphDatabaseService.saveTradeKindToNeo4j();
//		initGraphDatabaseService.saveRealEstateKindToNeo4j();
		initGraphDatabaseService.saveUserToNeo4j();
		return new ResponseEntity<>(initGraphDatabaseService.saveTradeToNeo4j(), HttpStatus.OK);
	}
}
