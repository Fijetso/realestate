package vn.edu.uit.realestate.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
