package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.User.UserService;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<MappingJacksonValue> getUsers() {
    	MappingJacksonValue users = userService.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<MappingJacksonValue> getUserById(@PathVariable long id) {
    	MappingJacksonValue foundUser = userService.findById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }
    @PostMapping("users/{userId}/trades")
    public ResponseEntity postTradeByUserId(@PathVariable long userId, @Valid @RequestBody Trade trade) throws Exception {
    	userService.addTradeToUser(userId, trade);
//    	URI location = ServletUriComponentsBuilder
//    			.fromCurrentRequest().path("/{id}")
//    			.buildAndExpand(trade.getId()).toUri();
//    	return ResponseEntity.created(location).build();
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
    	if(!userRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any User with Id="+id);
		}
    	userRepository.deleteById(id);
    }
}