package vn.edu.uit.realestate.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Service.EntityService.UserService;

@RestController
@RequestMapping("/secured/user/")
@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
public class UserInfoController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<MappingJacksonValue> getById(@PathVariable long id) {
		MappingJacksonValue foundUser = userService.findById(id);
		return new ResponseEntity<>(foundUser, HttpStatus.OK);
	}
}
