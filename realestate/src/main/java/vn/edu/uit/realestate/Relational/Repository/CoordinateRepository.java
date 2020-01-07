package vn.edu.uit.realestate.Relational.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Relational.Model.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {

}
