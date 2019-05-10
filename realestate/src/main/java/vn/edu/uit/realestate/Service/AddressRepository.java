package vn.edu.uit.realestate.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
