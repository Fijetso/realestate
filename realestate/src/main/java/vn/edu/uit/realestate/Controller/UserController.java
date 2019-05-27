package vn.edu.uit.realestate.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.EntityService.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<MappingJacksonValue> getUsers() {
		MappingJacksonValue users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<MappingJacksonValue> getUserById(@PathVariable long id) {
		MappingJacksonValue foundUser = userService.findById(id);
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> postUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("users/{userId}/trades")
	public ResponseEntity<Trade> postTradeByUserId(@PathVariable long userId, @Valid @RequestBody Trade trade)
			throws Exception {
		Trade savedTrade = userService.addTradeToUser(userId, trade);
		return new ResponseEntity<>(savedTrade, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/users/{id}")
	public ResponseEntity deleteUserById(@PathVariable long id) {
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}