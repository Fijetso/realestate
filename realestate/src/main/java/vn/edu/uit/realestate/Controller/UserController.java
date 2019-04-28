package vn.edu.uit.realestate.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
    	List<User> users = (List<User>) userRepository.findAll();
    	if (users.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any User");
    	}
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
    	Optional<User> foundUser = userRepository.findById(id);
		if (foundUser.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User with id="+id);
    	}
        return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
    	if(!userRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any User with Id="+id);
		}
    	userRepository.deleteById(id);
    }
}