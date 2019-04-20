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

import vn.edu.uit.realestate.Controller.ExceptionHandler.HistoryNotFoundException;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Repository.RealEstateKindRepository;

@RestController
public class RealEstateKindController {
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;

    @GetMapping("/realestatekinds")
    public Iterable<RealEstateKind> getRealEstateKinds() {
    	Iterable<RealEstateKind> realEstateKinds = realEstateKindRepository.findAll();
        return realEstateKinds;
    }
    @GetMapping("/realestatekinds/{id}")
    public Optional<RealEstateKind> getRealEstateById(@PathVariable long id) {
		try {
		    	Optional<RealEstateKind> foundRealEstate = realEstateKindRepository.findById(id);
		        return foundRealEstate;
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
		return null;
    }
    @PostMapping("/realestatekinds")
    public ResponseEntity<RealEstateKind> addRealEstateKind(@Valid @RequestBody RealEstateKind realEstateKind) {
    	realEstateKindRepository.save(realEstateKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(realEstateKind.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/realestatekinds/{id}")
    public void deleteHistoryById(@PathVariable long id) {
		try {
			realEstateKindRepository.deleteById(id);
		}catch(Exception e) {
			System.out.println("\nPROBLEM: "+e);
		}
    }
}