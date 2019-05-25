package vn.edu.uit.realestate.Controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.AddressTree.Province;
import vn.edu.uit.realestate.Service.Address.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressService addressService;

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
    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping("/addresstree")
    public Province initAddressTree() throws IOException{
    	ObjectMapper objectMapper = new ObjectMapper();
    	final Resource addressTreeResource = resourceLoader.getResource("classpath:static/addresstree.json");
    	File addressTreeFile = addressTreeResource.getFile();
    	if(addressTreeFile.exists()) {
    		return addressService.parseJSON(addressTreeFile.getPath());
    	}
    	else {
    		throw new NotFoundException("Cannot find "+addressTreeFile.getPath());
    	}
    	
    }
}