package vn.edu.uit.realestate.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@RestController
public class TradeController {
	@Autowired
	private TradeService tradeService;

    @GetMapping("/trades")
    public ResponseEntity<MappingJacksonValue> findAll() {
    	MappingJacksonValue foundTradeList = tradeService.findAll();
        return new ResponseEntity<>(foundTradeList,HttpStatus.OK);
    }
    @GetMapping("/trades/{id}")
    public ResponseEntity<MappingJacksonValue> findById(@PathVariable long id){
    	MappingJacksonValue foundTrade = tradeService.findById(id);
        return new ResponseEntity<>(foundTrade, HttpStatus.OK);
     }

    @DeleteMapping("/trades/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id)throws Exception {
    	tradeService.deleteById(id);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/trades/{tradeId}/bookings")
    public ResponseEntity<?> postBookingByTradeId(@PathVariable long tradeId, @Valid @RequestBody Booking booking)throws Exception {
    	tradeService.postBookingByTradeId(tradeId, booking);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/trades/{id}/addviewcount")
    public ResponseEntity<Long> addViewCountById(@PathVariable long id){
    	Long viewCount = tradeService.addViewToTrade(id);
        return new ResponseEntity<>(viewCount, HttpStatus.OK);
     }
}