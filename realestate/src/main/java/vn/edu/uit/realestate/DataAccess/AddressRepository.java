package vn.edu.uit.realestate.DataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.uit.realestate.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	List<Address> findByProvince(Long province);
	List<Address> findByDistrict(Long district);
	List<Address> findByWard(Long ward);
}
