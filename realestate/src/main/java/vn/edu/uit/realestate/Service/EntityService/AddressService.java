package vn.edu.uit.realestate.Service.EntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.Controller.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.Controller.ExceptionHandler.IllegalArgumentException;
import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.DataAccess.BluePrintRepository;
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.RealImageRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.BluePrint;
import vn.edu.uit.realestate.Model.Details;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.RealImage;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Model.AddressTree.District;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	public MappingJacksonValue findTradeByProvince(Long province) {
    	List<Address> addresses = (List<Address>) addressRepository.findByProvince(province);
		if (addresses.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade with province id="+province);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		List<Trade> tradeListByProvince = new ArrayList<Trade>();
		for(Address each:addresses) {
			tradeListByProvince.add(each.getTrade());
		}
		MappingJacksonValue mapping = new MappingJacksonValue(tradeListByProvince);
		mapping.setFilters(filters);
		return mapping;
	}
	public MappingJacksonValue findTradeByDistrict(Long district) {
    	List<Address> addresses = (List<Address>) addressRepository.findByDistrict(district);
		if (addresses.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade with district id="+district);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		List<Trade> tradeListByDistrict = new ArrayList<Trade>();
		for(Address each:addresses) {
			tradeListByDistrict.add(each.getTrade());
		}
		MappingJacksonValue mapping = new MappingJacksonValue(tradeListByDistrict);
		mapping.setFilters(filters);
		return mapping;
	}
	public MappingJacksonValue findTradeByWard(Long ward) {
    	List<Address> addresses = (List<Address>) addressRepository.findByWard(ward);
		if (addresses.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade with ward id="+ward);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		List<Trade> tradeListByWard = new ArrayList<Trade>();
		for(Address each:addresses) {
			tradeListByWard.add(each.getTrade());
		}
		MappingJacksonValue mapping = new MappingJacksonValue(tradeListByWard);
		mapping.setFilters(filters);
		return mapping;
	}
}
