package vn.edu.uit.realestate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Relational.Model.Request;
import vn.edu.uit.realestate.Service.EntityService.RequestService;

@RestController
public class RequestController {
	@Autowired
	private RequestService requestService;

	@GetMapping("/requests/{id}")
	public ResponseEntity<Request> getById(@PathVariable long id) {
		Request foundRequest = requestService.findById(id);
		return new ResponseEntity<>(foundRequest, HttpStatus.OK);
	}

//	@GetMapping("/users/{userId}/trades")
//	public ResponseEntity<MappingJacksonValue> getAllTradesByUserId(@PathVariable long userId){
//		MappingJacksonValue tradeList = userService.findAllTradeByUserId(userId);
//		return new ResponseEntity<>(tradeList, HttpStatus.OK);
//	}

	@DeleteMapping("/requests/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		requestService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}