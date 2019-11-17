package vn.edu.uit.realestate.Relational.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Trade;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

}
