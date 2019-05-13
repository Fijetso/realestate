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
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Service.RealEstateKindRepository;

@RestController
public class RealEstateKindController {
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;

    @GetMapping("/realestatekinds")
    public ResponseEntity<List<RealEstateKind>> getRealEstateKinds() {
        List<RealEstateKind> realEstateKinds = realEstateKindRepository.findAll();
    	if (realEstateKinds.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Real Estate Kind");
    	}
        return new ResponseEntity<>(realEstateKinds, HttpStatus.OK);
    }
    @GetMapping("/realestatekinds/{id}")
    public ResponseEntity<RealEstateKind> getRealEstateKindById(@PathVariable long id) {
	    Optional<RealEstateKind> foundRealEstate = realEstateKindRepository.findById(id);
		if (foundRealEstate.isPresent()==false) {
			throw new NotFoundException("Cannot find any Real Estate Kind with id="+id);
		}
	    return new ResponseEntity<>(foundRealEstate.get(), HttpStatus.OK);
	}

    @PostMapping("/realestatekinds")
    public ResponseEntity<RealEstateKind> postRealEstateKind(@Valid @RequestBody RealEstateKind realEstateKind) throws Exception {
    	realEstateKindRepository.save(realEstateKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(realEstateKind.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @GetMapping("/realestatekinds/{realEstateKindId}/trades")
    public ResponseEntity<List<Trade>> getTradesByRealEstateKindId(@PathVariable Long realEstateKindId) {
    	Optional<RealEstateKind> foundRealEstateKind = realEstateKindRepository.findById(realEstateKindId);
    	if (foundRealEstateKind.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Real Estate Kind with id="+realEstateKindId);
    	}
    	List<Trade> trades = foundRealEstateKind.get().getTrades();
    	if(trades.isEmpty() == true)
    		throw new NotFoundException("Cannot find any Trade with Real Estate Kind Id="+realEstateKindId);
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }
    @DeleteMapping("/realestatekinds/{id}")
    public void deleteRealEstateKindById(@PathVariable long id) throws Exception {
    	Optional<RealEstateKind> foundRealEstate = realEstateKindRepository.findById(id);
		if (foundRealEstate.isPresent()==false) {
			throw new NotFoundException("Cannot find any Real Estate Kind with id="+id);
		}	
    	if(foundRealEstate.get().getTrades().isEmpty()==false) {
    		throw new ExistContentException("There still exist 'Trade' in this Real Estate Kind. You should delete all these Trades before delete.");
    	}
    	realEstateKindRepository.deleteById(id);
    }
}