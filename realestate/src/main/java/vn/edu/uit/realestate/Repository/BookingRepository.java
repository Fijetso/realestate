package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
