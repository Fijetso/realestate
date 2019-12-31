package vn.edu.uit.realestate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Repository.GraphAddressRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Relational.Model.ModelMapper.SuggestModel;

@RestController
public class RecommenderSystemController {
	@Autowired
	private GraphAddressRepository graphAddressRepository;
	@Autowired
	private GraphTradeRepository graphTradeRepository;
	@PostMapping("trades/recommend")
    public ResponseEntity<List<GraphTrade>> findTradeByProvince(@RequestBody SuggestModel suggestModel) {
    	List<GraphTrade> foundTrade = graphTradeRepository.recommendTrades(suggestModel.getUserJob(), suggestModel.getDistrictIdList());
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
	@GetMapping("trades/suggest/{districtId}")
    public ResponseEntity<List<GraphTrade>> findTradeByDistrict(@PathVariable long districtId) {
    	List<GraphTrade> foundTrade = graphAddressRepository.findTradeByDistrictId(districtId);
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
}
