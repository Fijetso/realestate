package vn.edu.uit.realestate.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Repository.GraphAddressRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Relational.Model.ModelMapper.SuggestModel;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@RestController
public class RecommenderSystemController {
	@Autowired
	private GraphAddressRepository graphAddressRepository;
	@Autowired
	private GraphTradeRepository graphTradeRepository;
	@Autowired 
	private TradeService tradeService;
	@PostMapping("trades/recommend")
    public ResponseEntity<List<GraphTrade>> recommendTradeByProvince(@RequestBody SuggestModel suggestModel) {
    	List<GraphTrade> foundTrade = graphTradeRepository.recommendTrades(suggestModel.getUserJob(), suggestModel.getDistrictIdList());
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
	
	@GetMapping("trades/recommend/fengshui")
    public ResponseEntity<List<GraphTrade>> recommendTradeByUserAge(@RequestParam(value="birthdate") String birthdate, @RequestParam(value="isFemale") boolean isFemale) {
    	List<GraphTrade> foundTrade = tradeService.recommendTradesByUserAge(birthdate, isFemale);
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
	
	@GetMapping("trades/direction")
    public ResponseEntity<List<GraphTrade>> demoRecommend(@RequestParam String direction) {
    	List<GraphTrade> foundTrade = graphTradeRepository.recommendTradesByUserAge2(direction);
        return new ResponseEntity<>(foundTrade,HttpStatus.OK);
    }
}
