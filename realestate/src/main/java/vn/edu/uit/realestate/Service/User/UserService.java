package vn.edu.uit.realestate.Service.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.Controller.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;
	@Autowired
	private TradeKindRepository tradeKindRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DetailsRepository detailsRepository;
	
	@Override
	public MappingJacksonValue findAll() {
		List<User> users = (List<User>) userRepository.findAll();
		if (users.isEmpty() == true) {
    		throw new NotFoundException("Cannot find any User");
    	}
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
	    FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
	    MappingJacksonValue mapping = new MappingJacksonValue(users);
	    mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public MappingJacksonValue findById(Long id) {
		Optional<User> foundUser = userRepository.findById(id);
		if (foundUser.isPresent()==false) {
    		throw new NotFoundException("Cannot find any User with id="+id);
    	}
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("trades","password");
	    FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
	    MappingJacksonValue mapping = new MappingJacksonValue(foundUser.get());
	    mapping.setFilters(filters);
		return mapping;
	}
	public void addTradeToUser(Long id, Trade trade) {
		Optional<User> foundUser = userRepository.findById(id);
		trade.setUser(foundUser.get());
		
    	RealEstateKind realEstateKind = trade.getRealEstateKind();
    	if(realEstateKind==null || !realEstateKindRepository.findById(realEstateKind.getId()).isPresent()) {
    		throw new NotFoundException("You must enter suitable Real Easte Kind");
    	}
    	
    	TradeKind tradeKind = trade.getTradeKind();
    	if(tradeKind==null || 
    			!tradeKindRepository.findById(tradeKind.getId()).isPresent()) {
    		throw new NotFoundException("You must enter suitable Trade Kind");
    	}
    	
    	if(trade.getAddress()!=null) {
    		addressRepository.save(trade.getAddress());
    	}
    	
    	if(trade.getDetails()!=null) {
    		detailsRepository.save(trade.getDetails());
    	}
    	tradeRepository.save(trade);
	}
}
