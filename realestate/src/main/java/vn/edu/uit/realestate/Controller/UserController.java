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

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.Repository.AddressRepository;
import vn.edu.uit.realestate.Service.Repository.DetailsRepository;
import vn.edu.uit.realestate.Service.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Service.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Service.Repository.TradeRepository;
import vn.edu.uit.realestate.Service.Repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;
	@Autowired
	private TradeKindRepository tradeKindRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DetailsRepository detailsRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
    	List<User> users = (List<User>) userRepository.findAll();
    	if (users.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any User");
    	}
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
    	Optional<User> foundUser = userRepository.findById(id);
		if (foundUser.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User with id="+id);
    	}
        return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
    }
    @PostMapping("users/{userId}/trades")
    public ResponseEntity<Trade> postTradeByUserId(@PathVariable long userId, @Valid @RequestBody Trade trade) throws Exception {
    	Optional<User> foundUser = userRepository.findById(userId);
		if (foundUser.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User with id="+userId);
    	}
		trade.setUser(foundUser.get());
		
    	RealEstateKind realEstateKind = trade.getRealEstateKind();
    	if(realEstateKind==null || 
    			!realEstateKindRepository.findById(realEstateKind.getId()).isPresent()) {
    		throw new NotFoundException("You must enter suitable Real Easte Kind");
    	}
    	
    	TradeKind tradeKind = trade.getTradeKind();
    	if(tradeKind==null || 
    			!tradeKindRepository.findById(tradeKind.getId()).isPresent()) {
    		throw new NotFoundException("You must enter suitable Trade Kind");
    	}
    	
    	if(trade.getAddress()!=null) {
    		addressRepository.save(trade.getAddress());
    	}
    	
    	if(trade.getDetails()!=null) {
    		detailsRepository.save(trade.getDetails());
    	}
    	tradeRepository.save(trade);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(trade.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
    	if(!userRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any User with Id="+id);
		}
    	userRepository.deleteById(id);
    }
}