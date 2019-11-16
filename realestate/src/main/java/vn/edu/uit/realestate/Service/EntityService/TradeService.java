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

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.IllegalArgumentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.Booking;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Relational.Model.Enum.TradeStatus;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.BookingRepository;
import vn.edu.uit.realestate.Relational.Repository.CoordinateRepository;
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
	private RealEstateKindRepository realEstateKindRepository;
	@Autowired
	private CoordinateRepository coordinateRepository;
	@Autowired
	private TradeKindRepository tradeKindRepository;
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
	private WardRepository wardRepository;
	@Autowired
	private UserRepository userRepository;

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

	public MappingJacksonValue updateTradeStatus(Long id, String newStatus) {
		Optional<Trade> foundTrade = tradeRepository.findById(id);
		if (foundTrade.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade with id=" + id);
		}
		try {
			TradeStatus newTradeStatus = TradeStatus.valueOf(newStatus.toUpperCase());
			foundTrade.get().setTradeStatus(newTradeStatus);
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find any Trade Status like " + newStatus);
		}
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("CoordinateFilter", filterExceptTrade).addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(tradeRepository.save(foundTrade.get()));
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

	public Trade saveTradeGraphQL(final String description, final Long cost, final Long userId,
			final Long realEstateKindId, final Long tradeKindId, final String detailAddress, final Long wardId,
			final Long length, final Long width, final Long square, final String direction, final String floors,
			final String legalDocuments, final int bathrooms, final int bedrooms, final String utilities,
			final String others, final Long longitude, final Long latitude) {
		Optional<User> user = userRepository.findById(userId);
		user.orElseThrow(
				() -> new CustomGraphQLException(400, "Not Found Exception: Cannot find any User Id=" + userId));

		Optional<RealEstateKind> realEstateKind = realEstateKindRepository.findById(realEstateKindId);
		realEstateKind.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find any Real Estate Kind Id=" + realEstateKindId));

		Optional<TradeKind> tradeKind = tradeKindRepository.findById(tradeKindId);
		tradeKind.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find any Trade Kind Id=" + tradeKindId));

		Optional<Ward> ward = wardRepository.findById(wardId);
		ward.orElseThrow(
				() -> new CustomGraphQLException(400, "Not Found Exception: Cannot find any Ward Id=" + wardId));

		Address address = new Address(detailAddress, ward.get().getId(), ward.get().getDistrict().getId(),
				ward.get().getDistrict().getProvince().getId());

		Details details = new Details(length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms,
				utilities, others);
		Coordinate coordinate = new Coordinate(longitude, latitude);

		Trade trade = new Trade(description, cost, user.get(), realEstateKind.get(), tradeKind.get(), address, details,
				coordinate);
		return tradeRepository.save(trade);
	}

	public Trade udpateTradeGraphQL(Long tradeId, String description, Long cost, Long realEstateKindId,
			Long tradeKindId, String detailAddress, Long wardId, Long length, Long width, Long square, String direction,
			String floors, String legalDocuments, int bathrooms, int bedrooms, String utilities, String others,
			Long longitude, Long latitude, String tradeStatus) {
		Optional<Trade> trade = tradeRepository.findById(tradeId);
		trade.orElseThrow(
				() -> new CustomGraphQLException(400, "Not Found Exception: Cannot find any Trade Id=" + tradeId));
		Trade foundTrade = trade.get();
		if (description != null) {
			foundTrade.setDescription(description);
		}
		if (cost != null) {
			foundTrade.setCost(cost);
		}
		if (realEstateKindId != null) {
			Optional<RealEstateKind> realEstateKind = realEstateKindRepository.findById(realEstateKindId);
			realEstateKind.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Real Estate Kind Id=" + realEstateKindId));
			foundTrade.setRealEstateKind(realEstateKind.get());
		}
		if (tradeKindId != null) {
			Optional<TradeKind> tradeKind = tradeKindRepository.findById(tradeKindId);
			tradeKind.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Trade Kind Id=" + tradeKindId));
			foundTrade.setTradeKind(tradeKind.get());
		}
		if (detailAddress != null || wardId != null) {
			Optional<Address> address = addressRepository.findById(foundTrade.getAddress().getId());
			address.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Address Kind Id=" + foundTrade.getAddress().getId()));
			Address foundAddress = address.get();

			foundAddress.setDetail(detailAddress != null ? detailAddress : foundAddress.getDetail());

			if (wardId != null) {
				Optional<Ward> ward = wardRepository.findById(wardId);
				ward.orElseThrow(() -> new NotFoundException("Cannot find any Ward Id=" + wardId));
				foundAddress.setWard(wardId);
				foundAddress.setDistrict(ward.get().getDistrict().getId());
				foundAddress.setCityOrProvince(ward.get().getDistrict().getProvince().getId());
			}
			addressRepository.save(foundAddress);
		}
		if (length != null || width != null || square != null || direction != null || floors != null
				|| legalDocuments != null || bathrooms >= 0 || bedrooms >= 0 || utilities != null || others != null) {
			Optional<Details> details = detailsRepository.findById(foundTrade.getDetails().getId());
			details.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Details Id=" + foundTrade.getDetails().getId()));
			Details foundDetails = details.get();

			foundDetails.setLength(length != null ? length : foundDetails.getLength());
			foundDetails.setWidth(width != null ? width : foundDetails.getWidth());
			foundDetails.setSquare(square != null ? square : foundDetails.getSquare());
			foundDetails.setDirection(direction != null ? direction : foundDetails.getDirection());
			foundDetails.setFloors(floors != null ? floors : foundDetails.getFloors());
			foundDetails.setLegalDocuments(legalDocuments != null ? legalDocuments : foundDetails.getLegalDocuments());
			foundDetails.setBathrooms(bathrooms >= 0 ? bathrooms : foundDetails.getBathrooms());
			foundDetails.setBedrooms(bedrooms >= 0 ? bedrooms : foundDetails.getBedrooms());
			foundDetails.setUtilities(utilities != null ? utilities : foundDetails.getUtilities());
			foundDetails.setOthers(others != null ? others : foundDetails.getOthers());

			detailsRepository.save(foundDetails);
		}
		if (longitude != null && latitude != null) {
			Optional<Coordinate> coordinate = coordinateRepository.findById(foundTrade.getCoordinate().getId());
			coordinate.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Coordinate Id=" + foundTrade.getCoordinate().getId()));
			Coordinate foundCoordinate = coordinate.get();
			foundCoordinate.setLongitude(longitude != null ? longitude : foundCoordinate.getLongitude());
			foundCoordinate.setLatitude(latitude != null ? latitude : foundCoordinate.getLatitude());

			coordinateRepository.save(foundCoordinate);
		}

		try {
			if (tradeStatus != null) {
				TradeStatus tradeStatusEnum = TradeStatus.valueOf(tradeStatus.toUpperCase());
				foundTrade.setTradeStatus(tradeStatusEnum);
			}
		} catch (Exception e) {
			throw new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Trade Status like " + tradeStatus);
		}
		return tradeRepository.save(foundTrade);
	}
}
