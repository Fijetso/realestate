package vn.edu.uit.realestate.Controller;


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

import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Service.EntityService.FavoriteTradeService;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@RestController
public class FavoriteTradeController {
	@Autowired
	private TradeService tradeService;
	@Autowired
	private FavoriteTradeService favoriteTradeService;

//    @GetMapping("/trades")
//    public ResponseEntity<MappingJacksonValue> findAll() {
//    	MappingJacksonValue foundTradeList = tradeService.findAll();
//        return new ResponseEntity<>(foundTradeList,HttpStatus.OK);
//    }
//    @GetMapping("/trades/{id}")
//    public ResponseEntity<MappingJacksonValue> findById(@PathVariable long id){
//    	MappingJacksonValue foundTrade = tradeService.findById(id);
//        return new ResponseEntity<>(foundTrade, HttpStatus.OK);
//     }
//    @DeleteMapping("/trades/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable long id)throws Exception {
//    	tradeService.deleteById(id);
//    	return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/trades/{tradeId}/bookings")
//    public ResponseEntity<List<Booking>> findBookingsByTradeId(@PathVariable long tradeId) {
//    	Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
//		if (foundTrade.isPresent()==false) {
//    		throw new NotFoundException("Cannot find any Trade with id="+tradeId);
//    	}
//		List<Booking> bookings = foundTrade.get().getBookings();
//    	if (bookings.isEmpty()== true) {
//    		throw new NotFoundException("Cannot find any Booking with Trade Id="+tradeId);
//    	}
//        return new ResponseEntity<>(bookings,HttpStatus.OK);
//    }
    @PostMapping("/users/{userId}/favoritetrade/{tradeId}")
    public ResponseEntity<?> postBookingByTradeId(@PathVariable(name="userId") long userId, @PathVariable(name="tradeId") long tradeId)throws Exception {
    	favoriteTradeService.save(userId, tradeId);;
    	return new ResponseEntity<>(HttpStatus.OK);
    }
//    @GetMapping("/trades/{id}/addviewcount")
//    public ResponseEntity<Long> addViewCountById(@PathVariable long id){
//    	Long viewCount = tradeService.addViewToTrade(id);
//        return new ResponseEntity<>(viewCount, HttpStatus.OK);
//     }
}