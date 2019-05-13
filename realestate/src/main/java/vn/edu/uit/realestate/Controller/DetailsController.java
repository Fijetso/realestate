package vn.edu.uit.realestate.Controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Details;
import vn.edu.uit.realestate.Service.DetailsRepository;

@RestController
public class DetailsController {
	@Autowired
	private DetailsRepository detailsRepository;

    @GetMapping("/details")
    public ResponseEntity<List<Details>> getDetails() {
    	List<Details> details = (List<Details>) detailsRepository.findAll();
    	if (details.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Details");
    	}
        return new ResponseEntity<>(details,HttpStatus.OK);
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