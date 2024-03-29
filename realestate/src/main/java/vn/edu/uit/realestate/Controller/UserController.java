package vn.edu.uit.realestate.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;
import vn.edu.uit.realestate.Relational.Model.Request;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Service.EntityService.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/secured/admin")
	public String helloAdmin() {
		return "Hello admin";
	}

	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_MANAGER')")
	@GetMapping("/secured/user")
	public String helloUser() {
		return "Hello user";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') OR hasRole('ROLE_ADMIN')")
	@GetMapping("/secured/manager")
	public String helloManager() {
		return "Hello manager";
	}



	@Autowired
	private GraphUserRepository graphUserRepository;


//	@GetMapping("/graph/saveusers")
//	public ResponseEntity<GraphUser> saveByGraph() {
//		GraphUser saveUser = new GraphUser();
//		saveUser.setName("Nguyễn Thị Ngọc Duyên");
//		saveUser.setGender(false);
//		/// Xài chung id user relational và graph
//		GraphUser result = graphUserRepository.save(saveUser);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}



	@PostMapping("/users")
	public ResponseEntity<?> post(@Valid @RequestBody User user) {
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}/trades")
	public ResponseEntity<MappingJacksonValue> getAllTradesByUserId(@PathVariable long userId) {
		MappingJacksonValue tradeList = userService.findAllTradeByUserId(userId);
		return new ResponseEntity<>(tradeList, HttpStatus.OK);
	}

	@PostMapping("users/{userId}/trades")
	public ResponseEntity<?> postTradeByUserId(@PathVariable long userId, @Valid @RequestBody Trade trade)
			throws Exception {
		userService.postTradeToUser(userId, trade);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("users/{userId}/requests")
	public ResponseEntity<?> postRequestByUserId(@PathVariable long userId, @Valid @RequestBody Request request)
			throws Exception {
		userService.postRequestToUser(userId, request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}