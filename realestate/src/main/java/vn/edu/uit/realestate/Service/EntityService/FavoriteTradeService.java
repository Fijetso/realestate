package vn.edu.uit.realestate.Service.EntityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.FavoriteTrade;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.FavoriteTradeRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class FavoriteTradeService implements IEntityService {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FavoriteTradeRepository favoriteTradeRepository;
	@Autowired
	private RealImageRepository realImageRepository;
	@Autowired
	private BluePrintRepository bluePrintRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DetailsRepository detailsRepository;

	@Override
	public MappingJacksonValue findAll() {
		List<Trade> trades = tradeRepository.findAll();
		if (trades.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade");
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(trades);
		mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public MappingJacksonValue findById(Long id) {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(foundTrade);
		mapping.setFilters(filters);
		return mapping;
	}

	@Override
	public void deleteById(Long id) {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		if (foundTrade.get().getBookings().isEmpty() == false) {
			throw new ExistContentException("Cannot delete this Trade. This Trade have existed Booking(s)");
		}
		Long tradeAddressId = foundTrade.get().getAddress().getId();
		if(addressRepository.existsById(tradeAddressId)) {
			addressRepository.deleteById(tradeAddressId);
		}
		Long tradeDetailsId = foundTrade.get().getDetails().getId();
		if(detailsRepository.existsById(tradeDetailsId)) {
			detailsRepository.deleteById(tradeDetailsId);
		}
		/// Get all images from Blueprints and RealImages.
		List<RealImage> tradeRealImages = foundTrade.get().getRealImages();
		if (tradeRealImages.isEmpty() == false) {
			realImageRepository.deleteAll(tradeRealImages);
		}
		List<BluePrint> tradeBluePrints = foundTrade.get().getBluePrints();
		if (tradeBluePrints.isEmpty() == false) {
			bluePrintRepository.deleteAll(tradeBluePrints);
		}
		tradeRepository.deleteById(id);
	}
	
	public void save(Long userId, Long tradeId) {
		Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + tradeId);
		}
		Optional<User> foundUser = userRepository.findById(userId);
		if (foundUser.isPresent() == false) {
			throw new NotFoundException("Cannot find any User with id=" + userId);
		}
		FavoriteTrade fav = new FavoriteTrade();
		fav.setId(userId,tradeId);
		fav.setUser(foundUser.get());
		fav.setTrade(foundTrade.get());
		favoriteTradeRepository.save(fav);
	}

//	public MappingJacksonValue findAllTradeByUserId(Long userId) {
//		Optional<User> foundUser = userRepository.findById(userId);
//		if (foundUser.isPresent() == false) {
//			throw new NotFoundException("Cannot find any User with id=" + userId);
//		}
//		List<Trade> foundTradeList = foundUser.get().getTrades();
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("user");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("TradeFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(foundTradeList);
//		mapping.setFilters(filters);
//		return mapping;
//	}
}
