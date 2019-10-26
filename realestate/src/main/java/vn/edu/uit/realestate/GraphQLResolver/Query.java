package vn.edu.uit.realestate.GraphQLResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.RealImageRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.BluePrint;
import vn.edu.uit.realestate.Model.Details;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.RealImage;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@Component
public class Query implements GraphQLQueryResolver {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private TradeService tradeService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RealEstateKindRepository realEstateKindRepository;
	@Autowired
	private TradeKindRepository tradeKindRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private DetailsRepository detailsRepository;
	@Autowired
	private RealImageRepository realImageRepository;
	
	public List<Trade> getTrades(final int count){
		List<Trade> foundTrades = tradeService.findAllGraphQL(count);
		return foundTrades;
	}
	public Optional<User> getAuthor(Trade trade) {
		return userRepository.findById(trade.getUser().getId());
	}
	public Optional<RealEstateKind> getRealEstateKind(Trade trade) {
		return realEstateKindRepository.findById(trade.getRealEstateKind().getId());
	}
	public Optional<TradeKind> getTradeKind(Trade trade) {
		return tradeKindRepository.findById(trade.getTradeKind().getId());
	}
	public Optional<Address> getAddress(Trade trade) {
		return addressRepository.findById(trade.getAddress().getId());
	}
	public Optional<Details> getDetails(Trade trade) {
		return detailsRepository.findById(trade.getDetails().getId());
	}
	public List<RealImage> getRealImages(Trade trade) {
		List<RealImage> result = realImageRepository.findByTrade(trade);
		return result;
	}
	public List<BluePrint> getBluePrints(Trade trade) {
		List<BluePrint> result = trade.getBluePrints();
		return result;
	}
}
