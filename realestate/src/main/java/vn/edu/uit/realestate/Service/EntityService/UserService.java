package vn.edu.uit.realestate.Service.EntityService;

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
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.Details;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class UserService implements IEntityService {
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
		if (foundUser.isPresent() == false) {
			throw new NotFoundException("Cannot find any User with id=" + id);
		}
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(foundUser.get());
		mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("The 'id' must not be null");
		}
		if (!userRepository.existsById(id)) {
			throw new NotFoundException("Cannot find any User with Id=" + id);
		}
		userRepository.deleteById(id);
	}

	public User save(User user) {
		if (user == null) {
			throw new ExistContentException("User must not be null");
		}
		return userRepository.save(user);
	}

	public Trade addTradeToUser(Long userId, Trade trade) {
		Optional<User> foundUser = userRepository.findById(userId);
		trade.setUser(foundUser.get());

		RealEstateKind realEstateKind = trade.getRealEstateKind();
		if (realEstateKind == null || !realEstateKindRepository.findById(realEstateKind.getId()).isPresent()) {
			throw new NotFoundException("You must enter suitable Real Easte Kind");
		}

		TradeKind tradeKind = trade.getTradeKind();
		if (tradeKind == null || !tradeKindRepository.findById(tradeKind.getId()).isPresent()) {
			throw new NotFoundException("You must enter suitable Trade Kind");
		}
		Address tradeAddress = trade.getAddress();
		if (tradeAddress != null) {
			tradeAddress.setTrade(trade);
			addressRepository.save(trade.getAddress());
		}
		Details tradeDetails = trade.getDetails();
		if (tradeDetails != null) {
			tradeDetails.setTrade(trade);
			detailsRepository.save(trade.getDetails());
		}
//    	List<Image> images = trade.getBluePrints();
//    	images.addAll(trade.getRealImages());
//    	imageRepository.save()

		return tradeRepository.save(trade);
	}
}
