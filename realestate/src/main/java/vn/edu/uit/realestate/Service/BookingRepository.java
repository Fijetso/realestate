package vn.edu.uit.realestate.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}