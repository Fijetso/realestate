package vn.edu.uit.realestate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
