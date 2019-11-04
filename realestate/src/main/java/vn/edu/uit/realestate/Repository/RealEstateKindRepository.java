package vn.edu.uit.realestate.DataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.RealEstateKind;

public interface RealEstateKindRepository extends JpaRepository<RealEstateKind, Long>{

}