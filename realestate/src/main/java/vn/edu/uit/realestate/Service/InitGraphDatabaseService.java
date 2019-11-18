package vn.edu.uit.realestate.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Graph.Model.GraphAddress;
import vn.edu.uit.realestate.Graph.Model.GraphBluePrint;
import vn.edu.uit.realestate.Graph.Model.GraphCoordinate;
import vn.edu.uit.realestate.Graph.Model.GraphDetails;
import vn.edu.uit.realestate.Graph.Model.GraphRealEstateKind;
import vn.edu.uit.realestate.Graph.Model.GraphRealImage;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;
import vn.edu.uit.realestate.Graph.Model.GraphUser;
import vn.edu.uit.realestate.Graph.Repository.GraphAddressRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphDetailsRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphRealEstateKindRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphRealImageRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeKindRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.BluePrint;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.RealImage;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;

@Service
public class InitGraphDatabaseService {

	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	GraphTradeRepository graphTradeRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	GraphAddressRepository graphAdddressRepository;
	@Autowired
	TradeKindRepository tradeKindRepository;
	@Autowired
	GraphTradeKindRepository graphTradeKindRepository;
	@Autowired
	RealEstateKindRepository realEstateKindRepository;
	@Autowired
	GraphRealEstateKindRepository graphRealEstateKindRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	GraphUserRepository graphUserRepository;
	@Autowired
	RealImageRepository realImageRepository;
	@Autowired
	GraphRealImageRepository graphRealImageRepository;
	@Autowired
	GraphDetailsRepository graphDetailsRepository;

	public void deleteEntireNeo4j() {
		graphTradeRepository.deleteEntireNeo4j();
	}

	public Iterable<GraphTradeKind> saveTradeKindToNeo4j() {
		List<TradeKind> tradeKindListInMySQL = tradeKindRepository.findAll();
		List<GraphTradeKind> convertedGraphTradeKindList = new ArrayList<>();
		for (TradeKind element : tradeKindListInMySQL) {
			GraphTradeKind graphTradeKind = new GraphTradeKind(element.getId(), element.getName());
			convertedGraphTradeKindList.add(graphTradeKind);
		}
		return graphTradeKindRepository.saveAll(convertedGraphTradeKindList);
	}

	public Iterable<GraphRealEstateKind> saveRealEstateKindToNeo4j() {
		List<RealEstateKind> realEstateKindListInMySQL = realEstateKindRepository.findAll();
		List<GraphRealEstateKind> convertedRealEstateKindList = new ArrayList<>();
		for (RealEstateKind element : realEstateKindListInMySQL) {
			GraphRealEstateKind graphRealEstateKind = new GraphRealEstateKind(element.getId(), element.getName());
			convertedRealEstateKindList.add(graphRealEstateKind);
		}
		return graphRealEstateKindRepository.saveAll(convertedRealEstateKindList);
	}

	public Iterable<GraphUser> saveUserToNeo4j() {
		List<User> userListInMySQL = userRepository.findAll();
		List<GraphUser> convertedUserList = new ArrayList<>();
		for (User element : userListInMySQL) {
			String job = element.getJob() == null ? "" : element.getJob().getName();
			GraphUser graphUser = new GraphUser(element.getId(), element.getName(), element.getEmail(),
					element.getPhone(), element.getBirthdate(), element.getGender(), job);
			convertedUserList.add(graphUser);
		}
		return graphUserRepository.saveAll(convertedUserList);
	}

	private List<GraphRealImage> convertRealImages(List<RealImage> realImages) {
		List<GraphRealImage> convertedRealImages = new ArrayList<>();
		for (RealImage element : realImages) {
			GraphRealImage graphRealImage = new GraphRealImage(element.getId(), element.getImageLink(),
					element.getDescription());
			convertedRealImages.add(graphRealImage);
		}
		return (List<GraphRealImage>) graphRealImageRepository.saveAll(convertedRealImages);
	}

	private List<GraphBluePrint> convertBluePrints(List<BluePrint> bluePrints) {
		List<GraphBluePrint> convertedBluePrints = new ArrayList<>();
		for (BluePrint element : bluePrints) {
			GraphBluePrint graphBluePrint = new GraphBluePrint(element.getId(), element.getImageLink(),
					element.getDescription());
			convertedBluePrints.add(graphBluePrint);
		}
		return convertedBluePrints;
	}

	private GraphUser convertUser(User user) {
		GraphUser graphUser = new GraphUser();
		if (user != null) {
			String job = user.getJob() == null ? "" : user.getJob().getName();
			graphUser = new GraphUser(user.getId(), user.getName(), user.getEmail(), user.getPhone(),
					user.getBirthdate(), user.getGender(), job);
		}
		return graphUser;
	}

	private GraphDetails convertDetails(Details details) {
		GraphDetails graphDetails = new GraphDetails();
		if (details != null) {
			graphDetails.setId(details.getId());
			if (graphDetails.getLength() != 0) {
				graphDetails.setLength(details.getLength());
			}
			if (graphDetails.getWidth() != 0) {
				graphDetails.setWidth(details.getWidth());
			}
			if (graphDetails.getSquare() != 0) {
				graphDetails.setSquare(details.getSquare());
			}
			if (graphDetails.getDirection() != null) {
				graphDetails.setDirection(details.getDirection());
			}
			if (graphDetails.getFloors() != null) {
				graphDetails.setFloors(details.getFloors());
			}
			if (graphDetails.getLegalDocuments() != null) {
				graphDetails.setLegalDocuments(details.getLegalDocuments());
			}
			if (graphDetails.getOthers() != null) {
				graphDetails.setOthers(details.getOthers());
			}
			graphDetails.setBathrooms(details.getBathrooms());
			graphDetails.setBedrooms(details.getBedrooms());
		}
		return graphDetails;
	}

	public Iterable<GraphTrade> saveTradeToNeo4j() {
		List<Trade> tradeListInMySQL = tradeRepository.findAll();
		List<GraphTrade> convertedGraphTradeList = new ArrayList<>();
		for (Trade element : tradeListInMySQL) {
			GraphTrade graphTrade = new GraphTrade();

			graphTrade.setId(element.getId());
			graphTrade.setDescription(element.getDescription());
			graphTrade.setCost(element.getCost());
			graphTrade.setTradeStatus(element.getTradeStatus() == null ? null : element.getTradeStatus().toString());

			Address address = element.getAddress();
			if (address != null) {
				GraphAddress graphAddress = new GraphAddress(address.getId(), address.getDetail(), address.getWard(),
						address.getDistrict(), address.getCityOrProvince());
				graphTrade.setAddress(graphAddress);
			}
			graphTrade.setDetails(convertDetails(element.getDetails()));
			Coordinate coordinate = element.getCoordinate();
			if (coordinate != null) {

				GraphCoordinate graphCoordinate = new GraphCoordinate(coordinate.getId(), coordinate.getLongitude(),
						coordinate.getLatitude());
				graphTrade.setCoordinate(graphCoordinate);
			}
			graphTrade
					.setTradeKind(new GraphTradeKind(element.getTradeKind().getId(), element.getTradeKind().getName()));

			graphTrade.setRealEstateKind(new GraphRealEstateKind(element.getRealEstateKind().getId(),
					element.getRealEstateKind().getName()));

			graphTrade.setRealImages(convertRealImages(element.getRealImages()));

			graphTrade.setBluePrints(convertBluePrints(element.getBluePrints()));

			graphTrade.setUser(convertUser(element.getUser()));

			convertedGraphTradeList.add(graphTrade);
		}
		return graphTradeRepository.saveAll(convertedGraphTradeList);
	}
}
