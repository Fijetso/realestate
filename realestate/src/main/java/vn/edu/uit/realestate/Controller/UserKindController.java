package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Model.UserKind;
import vn.edu.uit.realestate.Repository.UserKindRepository;
import vn.edu.uit.realestate.Repository.UserRepository;

@RestController
public class UserKindController {
	@Autowired
	private UserKindRepository userKindRepository;
	private UserRepository userRepository;

    @GetMapping("/userkinds")
    public List<UserKind> getUserKinds() {
    	List<UserKind> userKinds = userKindRepository.findAll();
        return userKinds;
    }
    @GetMapping("/userkind/{id}")
    public Optional<UserKind> getUserKind(@PathVariable long id) {
		try {
		    	Optional<UserKind> userKind = userKindRepository.findById(id);
		        return userKind;
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
		return null;
    }
//    @GetMapping("/userkind/{userKindId}/users")
//    public Page<User> getUsersByUserKindId(@PathVariable Long userKindId) {
//    	Page<User> users = userRepository.findByUserKindId(userKindId, PageRequest.of(0, 5));
//    	if(users == null)
//    		return null;
//        return users;
//    }
//    @GetMapping("/userkind/{userKindId}/users/{userId}")
//    public Optional<User> getUsersByIdAndUserKindId(@PathVariable (value = "userKindId") Long userKindId,
//    		@PathVariable (value = "userId") Long userId,
//            Pageable pageable) {
//    	Optional<User> users = userRepository.findByIdAndUserKindId(userId, userKindId);
//        return users;
//    }
    @PostMapping("/userkinds")
    public ResponseEntity<UserKind> postUserKind(@Valid @RequestBody UserKind userKind) {
    	userKindRepository.save(userKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(userKind.getId()).toUri();
    	///ResponseEntity return (status + header + body)
    	///Example: var headers = new HttpHeaders();
    	///headers.add("Responded", "MyController");
    	///ResponseEntity.accepted().headers(headers).body(c);
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/userkind/{id}")
    public void deleteUserKind(@PathVariable long id) {
		try {
		    	userKindRepository.deleteById(id);
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
    }
}