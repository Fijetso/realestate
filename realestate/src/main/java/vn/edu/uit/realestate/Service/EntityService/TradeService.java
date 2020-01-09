package vn.edu.uit.realestate.Service.EntityService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.Common.Common;
import vn.edu.uit.realestate.ExceptionHandler.BadRequestException;
import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.IllegalArgumentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.Enum.TradeStatus;
import vn.edu.uit.realestate.Relational.Model.ModelMapper.SuggestModel;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.BookingRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Service.IEntityService;
import vn.edu.uit.realestate.Service.ModelMapperService;

@Service
public class TradeService implements IEntityService {
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
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private GraphTradeRepository graphTradeRepository;
	@Autowired
	private ModelMapperService modelMapper;

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
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
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

	private Float mostFrequentMetterSquare(List<Float> array) {
		if (array == null) {
			return null;
		}
		// Sort the array
		Collections.sort(array);

		// find the max frequency using linear
		// traversal
		int max_count = 1;
		Float res = array.get(0);
		int curr_count = 1;

		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).equals(array.get(i - 1))) {
				curr_count++;
			} else {
				if (curr_count > max_count) {
					max_count = curr_count;
					res = array.get(i - 1);
				}
				curr_count = 1;
			}
		}

		// If last element is most frequent
		if (curr_count > max_count) {
			max_count = curr_count;
			res = array.get(array.size() - 1);
		}
		return res;
	}

	private Integer mostFrequentPriceBillion(List<Integer> array) {
		if (array == null) {
			return null;
		}
		// Sort the array
		Collections.sort(array);

		// find the max frequency using linear
		// traversal
		int max_count = 1;
		Integer res = array.get(0);
		int curr_count = 1;

		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).equals(array.get(i - 1))) {
				curr_count++;
			} else {
				if (curr_count > max_count) {
					max_count = curr_count;
					res = array.get(i - 1);
				}
				curr_count = 1;
			}
		}

		// If last element is most frequent
		if (curr_count > max_count) {
			max_count = curr_count;
			res = array.get(array.size() - 1);
		}
		return res;
	}

	public List<GraphTrade> recommendTradesWithContentBased(SuggestModel suggestModel) {
		Integer priceBillion = mostFrequentPriceBillion(suggestModel.getPriceList());
		Float metterSquare = mostFrequentMetterSquare(suggestModel.getSquareList());
		return graphTradeRepository.recommendTrades(suggestModel.getUserJob(), suggestModel.getDistrictIdList(),
				priceBillion, metterSquare);
	}

	public List<GraphTrade> recommendTradesByUserAge(String birthdate, Boolean isFemale) {
		if (birthdate == null) {
			throw new BadRequestException("We cannot recommend trade without birthdate. Please add your birthdate");
		}
		int birthYear;
		try {
			DateFormat dateFormat = new SimpleDateFormat(Common.Constains.LOCAL_DATE_FORMAT);
			Date parsedBirthdate = dateFormat.parse(birthdate);
			birthYear = parsedBirthdate.getYear() + 1900;
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Something went wrong with 'birthdate' variable. Be sure the date format is "
							+ Common.Constains.LOCAL_DATE_FORMAT);
		}
		int birthYearSum;
		do {
			birthYearSum =0;
			while (birthYear >= 1) {
				birthYearSum += birthYear % 10;
				birthYear /= 10;
			}
			birthYear = birthYearSum;
		} while (birthYearSum / 10 >= 1);
		birthYearSum = 11 - birthYearSum;
		if (isFemale) {
			birthYearSum = 15 - birthYearSum;
		}
		birthYear = birthYearSum;
		do {
			birthYearSum =0;
			while (birthYear >= 1) {
				birthYearSum += birthYear % 10;
				birthYear /= 10;
			}
			birthYear = birthYearSum;
		} while (birthYearSum / 10 >= 1);
		switch (birthYearSum) {
		case 1:
			return graphTradeRepository.recommendTradesByUserAge2(".*ắc");
		case 2:
			return graphTradeRepository.recommendTradesByUserAge(".*ây.*am");
		case 3:
			return graphTradeRepository.recommendTradesByUserAge(".*ông");
		case 4:
			return graphTradeRepository.recommendTradesByUserAge(".*ông.*am");
		case 5:
			return graphTradeRepository.recommendTradesByUserAge(".*");
		case 6:
			return graphTradeRepository.recommendTradesByUserAge(".*ây.*ắc");
		case 7:
			return graphTradeRepository.recommendTradesByUserAge(".*ây");
		case 8:
			return graphTradeRepository.recommendTradesByUserAge(".*ông.*ắc");
		case 9:
			return graphTradeRepository.recommendTradesByUserAge2(".*am");
		default:
		}
		return null;
	}

	public MappingJacksonValue updateTradeStatus(Long id, String newStatus) {
		Optional<Trade> trade = tradeRepository.findById(id);
		if (trade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		Trade foundTrade = trade.get();
		try {
			TradeStatus newTradeStatus = TradeStatus.valueOf(newStatus.toUpperCase());
			foundTrade.setTradeStatus(newTradeStatus);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find any Trade Status like " + newStatus);
		}
		graphTradeRepository.save(modelMapper.convertTrade(foundTrade));
		foundTrade = tradeRepository.save(foundTrade);

		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(foundTrade);
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
}
