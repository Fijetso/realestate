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

import vn.edu.uit.realestate.Controller.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Menu;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Repository.TradeKindRepository;

@RestController
public class TradeKindController {
	@Autowired
	private TradeKindRepository tradeKindRepository;

    @GetMapping("/tradekinds")
    public ResponseEntity<List<TradeKind>> getTradeKinds() {
        List<TradeKind> tradeKinds = tradeKindRepository.findAll();
    	if (tradeKinds.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Trade");
    	}
        return new ResponseEntity<>(tradeKinds, HttpStatus.OK);
    }
    @GetMapping("/tradekinds/{id}")
    public ResponseEntity<TradeKind> getTradeKindById(@PathVariable long id) {
	    Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(id);
		if (foundTradeKind.isPresent()==false) {
			throw new NotFoundException("Cannot find any Trade Kind with id="+id);
		}
	    return new ResponseEntity<>(foundTradeKind.get(), HttpStatus.OK);
	}
    @GetMapping("/tradekinds/{tradeKindId}/trades")
    public ResponseEntity<List<Trade>> getTradesByTradeKindId(@PathVariable Long tradeKindId) {
    	 Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(tradeKindId);
 		if (foundTradeKind.isPresent()==false) {
 			throw new NotFoundException("Cannot find any Trade Kind with id="+tradeKindId);
 		}
    	List<Trade> trades = foundTradeKind.get().getTrades();
    	if(trades.isEmpty() == true)
    		throw new NotFoundException("Cannot find any Trade with Trade Kind Id="+tradeKindId);
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }
    @PostMapping("/tradekinds")
    public ResponseEntity<TradeKind> postTradeKind(@Valid @RequestBody TradeKind tradeKind) throws Exception {
    	tradeKindRepository.save(tradeKind);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest().path("/{id}")
    			.buildAndExpand(tradeKind.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/tradekinds/{id}")
    public void deleteTradeKindById(@PathVariable long id) throws Exception {
    	 Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(id);
 		if (foundTradeKind.isPresent()==false) {
 			throw new NotFoundException("Cannot find any Trade Kind with id="+id);
 		}
    	if(foundTradeKind.get().getTrades().isEmpty()==false) {
    		throw new ExistContentException("There still exist 'Trade' in this Trade Kind. You should delete all these Trades before delete.");
    	}
    	tradeKindRepository.deleteById(id);
    }
}