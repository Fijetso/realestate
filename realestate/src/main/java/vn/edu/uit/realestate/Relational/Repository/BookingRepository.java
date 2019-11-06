package vn.edu.uit.realestate.Relational.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long>{

}
