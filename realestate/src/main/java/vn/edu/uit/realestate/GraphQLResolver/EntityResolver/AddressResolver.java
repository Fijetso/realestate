package vn.edu.uit.realestate.GraphQLResolver.EntityResolver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.AddressTree.District;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Province;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.DistrictRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.ProvinceRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.WardRepository;

@Component
public class AddressResolver implements GraphQLResolver<Address> {

	@Autowired
	WardRepository wardRepository;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	ProvinceRepository provinceRepository;
	public String getWard(Address address) {
		Optional<Ward> result = wardRepository.findById(address.getWard());
		result.orElseThrow(()-> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find Ward Id = "+address.getWard()));
		return result.get().getPathWithType();
	}
	public String getDistrict(Address address) {
		Optional<District> result = districtRepository.findById(address.getDistrict());
		result.orElseThrow(()-> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find District Id = "+address.getDistrict()));
		return result.get().getPathWithType();
	}
	public String getProvince(Address address) {
		Optional<Province> result = provinceRepository.findById(address.getCityOrProvince());
		result.orElseThrow(()-> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find Province Id = "+address.getCityOrProvince()));
		return result.get().getNameWithType();
	}
}
