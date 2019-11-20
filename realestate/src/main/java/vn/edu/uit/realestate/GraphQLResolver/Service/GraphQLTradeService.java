package vn.edu.uit.realestate.GraphQLResolver.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Graph.Model.GraphAddress;
import vn.edu.uit.realestate.Graph.Model.GraphBluePrint;
import vn.edu.uit.realestate.Graph.Model.GraphRealEstateKind;
import vn.edu.uit.realestate.Graph.Model.GraphRealImage;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
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

//	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	public Trade udpateTradeGraphQL(Long tradeId, String description, Long cost, Long realEstateKindId,
			Long tradeKindId, String detailAddress, Long wardId, Long length, Long width, Long square, String direction,
			String floors, String legalDocuments, int bathrooms, int bedrooms, String utilities, String others,
			Long longitude, Long latitude, String tradeStatus){
		Optional<Trade> trade = tradeRepository.findById(tradeId);
		trade.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find any Trade in MySQL with Id=" + tradeId));
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
			Address updatedAddress = address.get();

			updatedAddress.setDetail(detailAddress != null ? detailAddress : updatedAddress.getDetail());

			if (wardId != null) {
				Optional<Ward> ward = wardRepository.findById(wardId);
				ward.orElseThrow(() -> new NotFoundException("Cannot find any Ward Id=" + wardId));
				updatedAddress.setWard(wardId);
				updatedAddress.setDistrict(ward.get().getDistrict().getId());
				updatedAddress.setCityOrProvince(ward.get().getDistrict().getProvince().getId());
			}
			foundTrade.setAddress(updatedAddress);
			addressRepository.save(updatedAddress);
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
			foundTrade.setDetails(foundDetails);
			detailsRepository.save(foundDetails);
		}

		if (longitude != null && latitude != null) {
			Optional<Coordinate> coordinate = coordinateRepository.findById(foundTrade.getCoordinate().getId());
			coordinate.orElseThrow(() -> new CustomGraphQLException(400,
					"Not Found Exception: Cannot find any Coordinate Id=" + foundTrade.getCoordinate().getId()));
			Coordinate foundCoordinate = coordinate.get();
			foundCoordinate.setLongitude(longitude != null ? longitude : foundCoordinate.getLongitude());
			foundCoordinate.setLatitude(latitude != null ? latitude : foundCoordinate.getLatitude());

			foundTrade.setCoordinate(foundCoordinate);
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

		graphTradeRepository.save(modelMapper.convertTrade(foundTrade));
		return tradeRepository.save(foundTrade);
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
		GraphTrade graphTrade = modelMapper.convertTrade(trade);
		graphTradeRepository.save(graphTrade);
		return tradeRepository.save(trade);
	}
}
