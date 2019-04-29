package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.edu.uit.realestate.Controller.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Model.UserKind;
import vn.edu.uit.realestate.Repository.UserKindRepository;
import vn.edu.uit.realestate.Repository.UserRepository;

@RestController
public class UserKindController {
	@Autowired
	private UserKindRepository userKindRepository;
	@Autowired
	private UserRepository userRepository;

    @GetMapping("/userkinds")
    public ResponseEntity<List<UserKind>> getUserKinds() {
    	List<UserKind> userKinds = userKindRepository.findAll();
    	if (userKinds.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any User Kind");
    	}
        return new ResponseEntity<>(userKinds,HttpStatus.OK);
    }
    @GetMapping("/userkinds/{id}")
    public ResponseEntity<UserKind> getUserKind(@PathVariable long id) {
    	Optional<UserKind> foundUserKind = userKindRepository.findById(id);
		if (foundUserKind.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User Kind with id="+id);
    	}
        return new ResponseEntity<>(foundUserKind.get(), HttpStatus.OK);
    }
    @GetMapping("/userkinds/{userKindId}/users")
    public ResponseEntity<List<User>> getUsersByUserKindId(@PathVariable Long userKindId) {
    	Optional<UserKind> foundUserKind = userKindRepository.findById(userKindId);
    	if (foundUserKind.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User Kind with id="+userKindId);
    	}
    	List<User> users = foundUserKind.get().getUsers();
    	if(users.isEmpty() == true)
    		throw new NotFoundException("Cannot find any User with User Kind Id="+userKindId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/userkinds")
    public ResponseEntity<UserKind> postUserKind(@RequestBody UserKind userKind) {
    	userKindRepository.save(userKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(userKind.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @PostMapping("/userkinds/{userKindId}/users")
    public ResponseEntity<User> postUser(@PathVariable (value = "userKindId") Long userKindId,@Valid @RequestBody User user) {
    	Optional<UserKind> foundUserKind = userKindRepository.findById(userKindId);
		if (foundUserKind.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User Kind with id="+userKindId);
    	}
        user.setUserKind(foundUserKind.get());
        userRepository.save(user);
    	URI location = ServletUriComponentsBuilder
    			.fromPath("users/{id}")
    			.buildAndExpand(user.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/userkinds/{id}")
    public void deleteUserKind(@PathVariable long id) {
    	Optional<UserKind> foundUserKind = userKindRepository.findById(id);
		if (foundUserKind.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User Kind with id="+id);
    	}
    	if(foundUserKind.get().getUsers().isEmpty()==false) {
    		throw new ExistContentException("There still exist 'User' in this User Kind. You should delete all these Users before delete.");
    	}
    	userKindRepository.deleteById(id);
    }
}