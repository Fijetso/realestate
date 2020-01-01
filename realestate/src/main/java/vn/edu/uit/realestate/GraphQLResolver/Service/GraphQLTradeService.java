package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.Category;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.News;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.CategoryRepository;
import vn.edu.uit.realestate.Relational.Repository.CoordinateRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.WardRepository;
import vn.edu.uit.realestate.Service.ModelMapperService;

@Service
public class GraphQLTradeService {
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;
	@Autowired
	private CoordinateRepository coordinateRepository;
	@Autowired
	private TradeKindRepository tradeKindRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DetailsRepository detailsRepository;
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private GraphTradeRepository graphTradeRepository;
	@Autowired
	private WardRepository wardRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapperService modelMapper;
	public Trade saveTradeGraphQL(Long tradeId, String description, Long cost, Long userId, Long realEstateKindId,
			Long tradeKindId, String detailAddress, Long wardId, Long length, Long width, Long square, String direction,
			String floors, String legalDocuments, int bathrooms, int bedrooms, String utilities, String others,
			Float longitude, Float latitude) {
		Trade trade = new Trade();
		if (tradeId != null) {
			Optional<Trade> foundTrade = tradeRepository.findById(tradeId);
			if (foundTrade.isPresent()) {
				trade = foundTrade.get();
			} else {
				trade.setId(tradeId);
			}
		}
		if (userId != null) {
			Optional<User> user = userRepository.findById(userId);
			user.orElseThrow(
					() -> new CustomGraphQLException(400, "Not Found Exception: Cannot find any User Id=" + userId));
		}
		if (description != null) {
			trade.setDescription(description);
		}

		if (cost != null) {
			trade.setCost(cost);
		}

		if (realEstateKindId != null) {
			Optional<RealEstateKind> realEstateKind = realEstateKindRepository.findById(realEstateKindId);
			realEstateKind.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Real Estate Kind Id=" + realEstateKindId));
			trade.setRealEstateKind(realEstateKind.get());
		}

		if (tradeKindId != null) {
			Optional<TradeKind> tradeKind = tradeKindRepository.findById(tradeKindId);
			tradeKind.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Trade Kind Id=" + tradeKindId));
			trade.setTradeKind(tradeKind.get());
		}

		if (detailAddress != null || wardId != null) {
			Address address = trade.getAddress() != null ? trade.getAddress() : new Address();
			if (detailAddress != null) {
				address.setDetail(detailAddress);
			}
			if (wardId != null) {
				Optional<Ward> ward = wardRepository.findById(wardId);
				ward.orElseThrow(() -> new CustomGraphQLException(400,"Not Found Exception: Cannot find any Ward Id=" + wardId));
				address.setWard(wardId);
				address.setDistrict(ward.get().getDistrict().getId());
				address.setCityOrProvince(ward.get().getDistrict().getProvince().getId());
			}
//			address = addressRepository.save(address);
			trade.setAddress(address);
		}

		if (length != null || width != null || square != null || direction != null || floors != null
				|| legalDocuments != null || bathrooms >= 0 || bedrooms >= 0 || utilities != null || others != null) {
			Details details = trade.getDetails() != null ? trade.getDetails() : new Details();
			if (length != null)
				details.setLength(length);
			if (width != null)
				details.setWidth(width);
			if (square != null)
				details.setSquare(square);
			if (direction != null)
				details.setDirection(direction);
			if (floors != null)
				details.setFloors(floors);
			if (legalDocuments != null)
				details.setLegalDocuments(legalDocuments);
			if (bathrooms >= 0)
				details.setBathrooms(bathrooms);
			if (bedrooms >= 0)
				details.setBedrooms(bedrooms);
			if (utilities != null)
				details.setUtilities(utilities);
			if (others != null)
				details.setOthers(others);
//			details = detailsRepository.save(details);
			trade.setDetails(details);
		}

		if (longitude != null && latitude != null) {
			Coordinate coordinate = trade.getCoordinate() != null ? trade.getCoordinate() : new Coordinate();
			coordinate.setLongitude(longitude != null ? longitude : coordinate.getLongitude());
			coordinate.setLatitude(latitude != null ? latitude : coordinate.getLatitude());
//			coordinate = coordinateRepository.save(coordinate);
			trade.setCoordinate(coordinate);
		}
		trade = tradeRepository.save(trade);
		graphTradeRepository.save(modelMapper.convertTrade(trade));
		return trade;
	}

//	public Trade saveTradeGraphQL(final Long tradeId, final String description, final Long cost, final Long userId,
//			final Long realEstateKindId, final Long tradeKindId, final String detailAddress, final Long wardId,
//			final Long length, final Long width, final Long square, final String direction, final String floors,
//			final String legalDocuments, final int bathrooms, final int bedrooms, final String utilities,
//			final String others, final Long longitude, final Long latitude) {
//
//		Optional<RealEstateKind> realEstateKind = realEstateKindRepository.findById(realEstateKindId);
//		realEstateKind.orElseThrow(() -> new CustomGraphQLException(400,
//				"Not Found Exception: Cannot find any Real Estate Kind Id=" + realEstateKindId));
//
//		Optional<TradeKind> tradeKind = tradeKindRepository.findById(tradeKindId);
//		tradeKind.orElseThrow(() -> new CustomGraphQLException(400,
//				"Not Found Exception: Cannot find any Trade Kind Id=" + tradeKindId));
//
//		Optional<Ward> ward = wardRepository.findById(wardId);
//		ward.orElseThrow(
//				() -> new CustomGraphQLException(400, "Not Found Exception: Cannot find any Ward Id=" + wardId));
//
//		Address address = new Address(detailAddress, ward.get().getId(), ward.get().getDistrict().getId(),
//				ward.get().getDistrict().getProvince().getId());
//
//		Details details = new Details(length, width, square, direction, floors, legalDocuments, bathrooms, bedrooms,
//				utilities, others);
//		Coordinate coordinate = new Coordinate(longitude, latitude);
//
//		Trade trade = new Trade(description, cost, user.get(), realEstateKind.get(), tradeKind.get(), address, details,
//				coordinate);
//		if (tradeId != null) {
//			trade.setId(tradeId);
//		}
//		trade = tradeRepository.save(trade);
//		GraphTrade graphTrade = modelMapper.convertTrade(trade);
//		graphTradeRepository.save(graphTrade);
//		return trade;
//	}
}
