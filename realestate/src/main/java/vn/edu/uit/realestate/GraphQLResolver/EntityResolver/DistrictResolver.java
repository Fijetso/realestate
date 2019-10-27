package vn.edu.uit.realestate.GraphQLResolver.EntityResolver;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import vn.edu.uit.realestate.Model.AddressTree.District;
import vn.edu.uit.realestate.Model.AddressTree.Province;

@Component
public class DistrictResolver implements GraphQLResolver<District> {

	public Province getProvince(District district) {
		Province result = district.getProvince();
		return result;
	}
}
