package vn.edu.uit.realestate.Controller;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Service.EntityService.TradeKindService;

@RestController
public class TradeKindController {
	@Autowired
	private TradeKindService tradeKindService;

    @GetMapping("/tradekinds")
    public ResponseEntity<List<TradeKind>> findAll() {
    	List<TradeKind> tradeKindList = tradeKindService.findAll();
        return new ResponseEntity<>(tradeKindList, HttpStatus.OK);
    }
    @GetMapping("/tradekinds/{id}")
    public ResponseEntity<TradeKind> findById(@PathVariable long id) {
    	TradeKind tradeKind = tradeKindService.findById(id);
	    return new ResponseEntity<>(tradeKind, HttpStatus.OK);
	}
    @GetMapping("/tradekinds/{tradeKindId}/trades")
    public ResponseEntity<MappingJacksonValue> findTradesByTradeKindId(@PathVariable Long tradeKindId) {
    	MappingJacksonValue tradeList = tradeKindService.getTradesByTradeKindId(tradeKindId);
        return new ResponseEntity<>(tradeList, HttpStatus.OK);
    }
    @PostMapping("/tradekinds")
    public ResponseEntity<?> save(@Valid @RequestBody TradeKind tradeKind) throws Exception {
    	tradeKindService.save(tradeKind);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/tradekinds/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) throws Exception {
    	tradeKindService.deleteById(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}