package vn.edu.uit.realestate.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Booking;
import vn.edu.uit.realestate.Repository.BookingRepository;

@RestController
public class BookingController {
	@Autowired
	private BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings() {
    	List<Booking> bookings = (List<Booking>) bookingRepository.findAll();
    	if (bookings.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any Booking");
    	}
        return new ResponseEntity<>(bookings,HttpStatus.OK);
    }
    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable long id) {
    	Optional<Booking> foundBooking = bookingRepository.findById(id);
		if (foundBooking.isPresent()==false) {
    		throw new NotFoundException("Cannot find any Booking with id="+id);
    	}
        return new ResponseEntity<>(foundBooking.get(), HttpStatus.OK);
    }
    
    @DeleteMapping("/bookings/{id}")
    public void deleteBookingById(@PathVariable long id) {
    	if(!bookingRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any Booking with Id="+id);
		}
    	bookingRepository.deleteById(id);
    }
}