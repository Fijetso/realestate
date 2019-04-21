package vn.edu.uit.realestate.Controller;


import java.net.URI;
import java.util.Iterator;
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
import vn.edu.uit.realestate.Model.Menu;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Repository.RealEstateKindRepository;

@RestController
public class RealEstateKindController {
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;

    @GetMapping("/realestatekinds")
    public ResponseEntity<Object> getRealEstateKinds() {
        List<RealEstateKind> realEstateKinds = realEstateKindRepository.findAll();
    	if (realEstateKinds.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Real Estate Kind");
    	}
        return new ResponseEntity<>(realEstateKinds, HttpStatus.OK);
    }
    @GetMapping("/realestatekinds/{id}")
    public ResponseEntity<Object> getRealEstateById(@PathVariable long id) {
	    Optional<RealEstateKind> foundRealEstate = realEstateKindRepository.findById(id);
		if (foundRealEstate.isPresent()==false) {
			throw new NotFoundException("Cannot find any Real Estate Kind with id="+id);
		}
	    return new ResponseEntity<>(foundRealEstate, HttpStatus.OK);
	}

    @PostMapping("/realestatekinds")
    public ResponseEntity<RealEstateKind> addRealEstateKind(@Valid @RequestBody RealEstateKind realEstateKind) throws Exception {
    	realEstateKindRepository.save(realEstateKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(realEstateKind.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/realestatekinds/{id}")
    public void deleteHistoryById(@PathVariable long id) throws Exception {
			realEstateKindRepository.deleteById(id);
    }
}