package vn.edu.uit.realestate.Controller.Admin;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Service.EntityService.UserService;

@RestController
@RequestMapping("/secured/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminUserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<MappingJacksonValue> getAll() {
		MappingJacksonValue users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<MappingJacksonValue> updateUserRole(@PathVariable Long userId,
			@RequestParam Set<String> roles) {
		MappingJacksonValue user = userService.updateUserRole(userId, roles);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
