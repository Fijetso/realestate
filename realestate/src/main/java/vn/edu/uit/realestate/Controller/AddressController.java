package vn.edu.uit.realestate.Controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.AddressTree.District;
import vn.edu.uit.realestate.Model.AddressTree.Province;
import vn.edu.uit.realestate.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Service.EntityService.AddressService;

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
    @GetMapping("/addresstree/init")
    public Province initAddressTree() throws IOException{
//    	ObjectMapper objectMapper = new ObjectMapper();
    	final Resource addressTreeResource = resourceLoader.getResource("classpath:static/addresstree.json");
    	File addressTreeFile = addressTreeResource.getFile();
    	if(addressTreeFile.exists()) {
    		return addressService.parseJSON(addressTreeFile.getPath());
    	}
    	else {
    		throw new NotFoundException("Cannot find "+addressTreeFile.getPath());
    	}
    }
    @GetMapping("/addresstree/provinces")
    public ResponseEntity<List<Province>> getProvince(){
    	List<Province> province = (List<Province>) addressService.getAllProvince();
    	if(province.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Province in Database. run initAddressTree controller for initing Address tree");
    	}
        return new ResponseEntity<>(province,HttpStatus.OK);
    }
    @GetMapping("/addresstree/provinces/{provinceId}/district")
    public ResponseEntity<List<District>> getDistrictByProvinceId(@PathVariable Long provinceId){
    	List<District> district = (List<District>) addressService.getAllDistrict(provinceId);
    	if(district.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any District in Database. run initAddressTree controller for initing Address tree");
    	}
        return new ResponseEntity<>(district,HttpStatus.OK);
    }
    @GetMapping("/addresstree/provinces/{provinceId}/district/{districtId}/ward")
    public ResponseEntity<List<Ward>> getWardByDistrictId(@PathVariable(name="provinceId") Long provinceId,@PathVariable(name="districtId") Long districtId){
    	List<Ward> ward = (List<Ward>) addressService.getAllWard(districtId);
    	if(ward.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Ward in Database. run initAddressTree controller for initing Address tree");
    	}
        return new ResponseEntity<>(ward,HttpStatus.OK);
    }
}