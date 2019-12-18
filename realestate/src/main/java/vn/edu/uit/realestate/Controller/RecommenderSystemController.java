package vn.edu.uit.realestate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Repository.GraphAddressRepository;

@RestController
public class RecommenderSystemController {
	@Autowired
	private GraphAddressRepository graphAddressRepository;
	@GetMapping("trades/suggest")
    public ResponseEntity<List<GraphTrade>> findTradeByProvince(@RequestParam long districtId) {
    	List<GraphTrade> foundTrade = graphAddressRepository.findTradeByDistrictId(districtId);
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
	@GetMapping("trades/suggest/h")
    public ResponseEntity<String> findTradeByProvince() {
//    	List<GraphTrade> foundTrade = graphAddressRepository.findTradeByDistrictId(districtId);
        return new ResponseEntity<>("Hello",HttpStatus.OK);
    }
}
