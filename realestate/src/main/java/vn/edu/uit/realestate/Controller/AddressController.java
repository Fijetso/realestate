package vn.edu.uit.realestate.Controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Service.Repository.AddressRepository;

@RestController
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddresses() {
    	List<Address> addresses = (List<Address>) addressRepository.findAll();
    	if (addresses.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Address");
    	}
        return new ResponseEntity<>(addresses,HttpStatus.OK);
    }
    @PostMapping("/addresses")
    public Address postAddress(@Valid @RequestBody Address address) {
    	Address savedAddress = addressRepository.save(address);
    	return savedAddress;
    }
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUser(@PathVariable long id) {
//    	Optional<User> foundUser = userRepository.findById(id);
//		if (foundUser.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any User with id="+id);
//    	}
//        return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
//    }
//    
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable long id) {
//    	if(!userRepository.existsById(id)) {
//			throw new NotFoundException("Cannot find any User with Id="+id);
//		}
//    	userRepository.deleteById(id);
//    }
}