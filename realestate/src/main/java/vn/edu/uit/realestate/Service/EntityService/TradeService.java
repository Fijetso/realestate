package vn.edu.uit.realestate.Service.EntityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.BookingRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.WardRepository;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class TradeService implements IEntityService {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private RealImageRepository realImageRepository;
	@Autowired
	private BluePrintRepository bluePrintRepository;
	@Autowired
	private BookingRepository bookingRepository;
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
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password",
				"requests", "favoriteTrades", "roles");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("CoordinateFilter", filterExceptTrade).addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(trades);
		mapping.setFilters(filters);
		return mapping;
	}

	public List<Trade> findAllGraphQL(final int count) {
		List<Trade> trades = tradeRepository.findAll().stream().limit(count).collect(Collectors.toList());
		if (trades.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade");
		}
		return trades;
	}

	public Optional<Trade> findByIdGraphQL(Long id) {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		return foundTrade;
	}

	@Override
	public MappingJacksonValue findById(Long id) {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("CoordinateFilter", filterExceptTrade).addFilter("TradeFilter", filterTrade);
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
		if (addressRepository.existsById(tradeAddressId)) {
			addressRepository.deleteById(tradeAddressId);
		}
		Long tradeDetailsId = foundTrade.get().getDetails().getId();
		if (detailsRepository.existsById(tradeDetailsId)) {
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

	public void postBookingByTradeId(Long tradeId, Booking booking) {
		Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + tradeId);
		}
		booking.setTrade(foundTrade.get());
		bookingRepository.save(booking);
	}

	public Long addViewToTrade(Long tradeId) {
		Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + tradeId);
		}
		tradeRepository.increaseViewCountById(tradeId);
		return foundTrade.get().getViewCount() + 1;
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RealEstateKindRepository realEstateKindRepository;
	@Autowired
	TradeKindRepository tradeKindRepository;
	@Autowired
	WardRepository wardRepository;

	public Trade saveTradeGraphQL(final String description, final Long cost, final Long userId, final Long realEstateKindId,
			final Long tradeKindId, final String detailAddress, final Long wardId, final Long length, final Long width,
			final Long square, final String direction, final String floors, final String legalDocuments,
			final int bathrooms, final int bedrooms, final String utilities, final String others) {
		Optional<User> user = userRepository.findById(userId);
		user.orElseThrow(() -> new NotFoundException("Cannot find any User Id=" + userId));

		Optional<RealEstateKind> realEstateKind = realEstateKindRepository.findById(realEstateKindId);
		user.orElseThrow(() -> new NotFoundException("Cannot find any Real Estate Kind Id=" + realEstateKindId));

		Optional<TradeKind> tradeKind = tradeKindRepository.findById(tradeKindId);
		user.orElseThrow(() -> new NotFoundException("Cannot find any Trade Kind Id=" + tradeKindId));

		Optional<Ward> ward = wardRepository.findById(wardId);
		user.orElseThrow(() -> new NotFoundException("Cannot find any Ward Id=" + wardId));
		Address address = new Address(detailAddress, ward.get().getId(), ward.get().getDistrict().getId(),
				ward.get().getDistrict().getProvince().getId());
		Details details = new Details(length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms,
				utilities, others);
		Trade trade = new Trade(description, cost, user.get(), realEstateKind.get(), tradeKind.get(), address, details);
		return tradeRepository.save(trade);
	}
}
