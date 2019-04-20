package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getUsers() {
    	Iterable<User> users = userRepository.findAll();
        return users;
    }
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable long id) {
		try {
		    	Optional<User> user = userRepository.findById(id);
		        return user;
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
		return null;
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
    	userRepository.save(user);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(user.getId()).toUri();
    	///ResponseEntity return (status + header + body)
    	///Example: var headers = new HttpHeaders();
    	///headers.add("Responded", "MyController");
    	///ResponseEntity.accepted().headers(headers).body(c);
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
		try {
		    	userRepository.deleteById(id);
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
    }
}