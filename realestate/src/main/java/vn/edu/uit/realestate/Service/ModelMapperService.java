package vn.edu.uit.realestate.Service;

import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.Graph.Model.GraphAddress;
import vn.edu.uit.realestate.Graph.Model.GraphCoordinate;
import vn.edu.uit.realestate.Graph.Model.GraphDetails;
import vn.edu.uit.realestate.Graph.Model.GraphRealEstateKind;
import vn.edu.uit.realestate.Graph.Model.GraphTrade;
import vn.edu.uit.realestate.Graph.Model.GraphTradeKind;
import vn.edu.uit.realestate.Graph.Model.GraphUser;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;

/**
 * Model Mapper Service is to convert from Relational Model to Graph Model.
 * Example: Trade -> GraphTrade
 */
@Service
public class ModelMapperService {

	public GraphUser convertUser(User user) {
		if (user == null) {
			return null;
		}
		GraphUser graphUser = new GraphUser();

		graphUser.setId(user.getId());
		graphUser.setName(user.getName());
		graphUser.setBirthdate(user.getBirthdate());
		graphUser.setGender(user.getGender());
		graphUser.setEmail(user.getEmail());
		graphUser.setPhone(user.getPhone());
		if (user.getJob() != null) {
			graphUser.setJob(user.getJob().getName());
		}
		return graphUser;
	}

	public GraphTrade convertTrade(Trade trade) {
		if (trade == null) {
			return null;
		}
		GraphTrade graphTrade = new GraphTrade();
		graphTrade.setId(trade.getId());
		graphTrade.setCost(trade.getCost());
		graphTrade.setDescription(trade.getDescription());
		graphTrade.setRealEstateKind(convertRealEstateKind(trade.getRealEstateKind()));
		graphTrade.setTradeKind(convertTradeKind(trade.getTradeKind()));
		graphTrade.setAddress(convertAddress(trade.getAddress()));
		graphTrade.setDetails(convertDetails(trade.getDetails()));
		graphTrade.setCoordinate(convertCoordinate(trade.getCoordinate()));
		graphTrade.setTradeStatus(trade.getTradeStatus().toString());
		return graphTrade;
	}

	public GraphCoordinate convertCoordinate(Coordinate coordinate) {
		if (coordinate == null) {
			return null;
		}
		return new GraphCoordinate(coordinate.getId(), coordinate.getLongitude(), coordinate.getLatitude());
	}

	public GraphDetails convertDetails(Details details) {
		if (details == null) {
			return null;
		}
		GraphDetails graphDetails = new GraphDetails();
		graphDetails.setId(details.getId());
		graphDetails.setBathrooms(details.getBathrooms());
		graphDetails.setBedrooms(details.getBedrooms());
		graphDetails.setDirection(details.getDirection());
		graphDetails.setFloors(details.getFloors());
		graphDetails.setLegalDocuments(details.getLegalDocuments());
		graphDetails.setSquare(details.getSquare());
		graphDetails.setLength(details.getLength());
		graphDetails.setWidth(details.getWidth());
		graphDetails.setUtilities(details.getUtilities());
		graphDetails.setOthers(details.getOthers());
		return graphDetails;
	}

	public GraphAddress convertAddress(Address address) {
		if (address == null) {
			return null;
		}
		return new GraphAddress(address.getId(), address.getDetail(), address.getWard(), address.getDistrict(),
				address.getCityOrProvince());
	}

	public GraphRealEstateKind convertRealEstateKind(RealEstateKind realEstateKind) {
		if (realEstateKind == null) {
			return null;
		}
		return new GraphRealEstateKind(realEstateKind.getId(), realEstateKind.getName());
	}

	public GraphTradeKind convertTradeKind(TradeKind tradeKind) {
		if (tradeKind == null) {
			return null;
		}
		return new GraphTradeKind(tradeKind.getId(), tradeKind.getName());
	}
}
