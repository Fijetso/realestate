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
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Repository.TradeRepository;

@RestController
public class TradeController {
	@Autowired
	private TradeRepository tradeRepository;

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getTrades() {
    	List<Trade> trades = tradeRepository.findAll();
    	if (trades.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any trade");
    	}
        return new ResponseEntity<>(trades,HttpStatus.OK);
    }
    @GetMapping("/trades/{id}")
    public ResponseEntity<Optional<Trade>> getTradeById(@PathVariable long id) throws Exception {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent()==false) {
    		throw new NotFoundException("Cannot find any trade with id="+id);
    	}
        return new ResponseEntity<>(foundTrade, HttpStatus.OK);
     }
    @PostMapping("/trades")
    public ResponseEntity<Trade> addTrade(@Valid @RequestBody Trade trade)throws Exception {
    	tradeRepository.save(trade);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(trade.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/trades/{id}")
    public void deleteHistoryById(@PathVariable long id)throws Exception {
    	if(!tradeRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any trade with Id="+id);
		}
		tradeRepository.deleteById(id);
    }
}