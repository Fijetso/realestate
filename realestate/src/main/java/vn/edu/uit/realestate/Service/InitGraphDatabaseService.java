package vn.edu.uit.realestate.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Graph.Model.GraphRealEstateKind;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;
import vn.edu.uit.realestate.Graph.Model.GraphUser;
import vn.edu.uit.realestate.Graph.Repository.GraphAddressRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphRealEstateKindRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeKindRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphTradeRepository;
import vn.edu.uit.realestate.Graph.Repository.GraphUserRepository;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.RealEstateKindRepository;
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

	public void saveTradeToNeo4j() {
		List<Trade> tradeListInMySQL = tradeRepository.findAll();
		List<GraphTrade> convertedGraphTradeList = new ArrayList<>();
		for( Trade element : tradeListInMySQL) {
//			Address address = element.getAddress();
//			GraphAddress graphAddress = new GraphAddress(address.getId(), address.getDetail(), address.getWard(), address.getDistrict(), address.getCityOrProvince());
//			GraphTrade convertedGraphElement = new GraphTrade(element.getId(), element.getDescription(), element.getCost(), element.getTradeStatus(), graphAddress,
//					graphDetails, graphCoordinate, realImages, bluePrints, );
//			Long id, String description, Long cost, TradeStatus tradeStatus, GraphAddress address,
//			GraphDetails details, GraphCoordinate coordinate, List<GraphRealImage> realImages,
//			List<GraphBluePrint> bluePrints)
		}
	}
}
