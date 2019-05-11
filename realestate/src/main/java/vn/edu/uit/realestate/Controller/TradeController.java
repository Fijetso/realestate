package vn.edu.uit.realestate.Controller;


import java.net.URI;
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
import vn.edu.uit.realestate.Model.Booking;
import vn.edu.uit.realestate.Model.Image;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Repository.BookingRepository;
import vn.edu.uit.realestate.Repository.ImageRepository;
import vn.edu.uit.realestate.Repository.TradeRepository;

@RestController
public class TradeController {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ImageRepository imageRepository;

    @GetMapping("/trades")
    public ResponseEntity<List<Trade>> getTrades() {
    	List<Trade> trades = tradeRepository.findAll();
    	if (trades.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Trade");
    	}
        return new ResponseEntity<>(trades,HttpStatus.OK);
    }
    @GetMapping("/trades/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable long id){
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Trade with id="+id);
    	}
        return new ResponseEntity<>(foundTrade.get(), HttpStatus.OK);
     }
    @GetMapping("/trades/{tradeId}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByTradeId(@PathVariable long tradeId) {
    	Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
		if (foundTrade.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Trade with id="+tradeId);
    	}
		List<Booking> bookings = foundTrade.get().getBookings();
    	if (bookings.isEmpty()== true) {
    		throw new NotFoundException("Cannot find any Booking with Trade Id="+tradeId);
    	}
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }
    @PostMapping("/trades/{tradeId}/bookings")
    public ResponseEntity<Trade> postBookingByTradeId(@PathVariable long tradeId, @Valid @RequestBody Booking booking)throws Exception {
    	Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
		if (foundTrade.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Trade with id="+tradeId);
    	}
		booking.setTrade(foundTrade.get());
    	bookingRepository.save(booking);
    	URI location = ServletUriComponentsBuilder.fromPath("bookings/{id}")
    			.buildAndExpand(booking.getId()).toUri();
    	return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/trades/{id}")
    public void deleteTradeById(@PathVariable long id)throws Exception {
    	Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Trade with id="+id);
    	}
    	if(foundTrade.get().getBookings().isEmpty()==false) {
    		throw new ExistContentException("Cannot delete this Trade. This Trade have existed Booking(s)");
    	}
    	///Get all images from Blueprints and RealImages.
    	List<Image> tradeImages = foundTrade.get().getRealImages();
    	tradeImages.addAll(foundTrade.get().getBluePrints());
    	if(tradeImages.isEmpty()==false) {
    		imageRepository.deleteAll(tradeImages);
    	}
		tradeRepository.deleteById(id);
    }
}