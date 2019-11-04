package vn.edu.uit.realestate.GraphQLResolver.EntityResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;

import vn.edu.uit.realestate.DataAccess.AddressRepository;
import vn.edu.uit.realestate.DataAccess.DetailsRepository;
import vn.edu.uit.realestate.DataAccess.RealEstateKindRepository;
import vn.edu.uit.realestate.DataAccess.RealImageRepository;
import vn.edu.uit.realestate.DataAccess.TradeKindRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.DataAccess.UserRepository;
import vn.edu.uit.realestate.DataAccess.AddressTree.WardRepository;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Model.Address;
import vn.edu.uit.realestate.Model.BluePrint;
import vn.edu.uit.realestate.Model.Details;
import vn.edu.uit.realestate.Model.RealEstateKind;
import vn.edu.uit.realestate.Model.RealImage;
import vn.edu.uit.realestate.Model.Trade;
import vn.edu.uit.realestate.Model.TradeKind;
import vn.edu.uit.realestate.Model.User;
import vn.edu.uit.realestate.Model.AddressTree.Ward;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

@Component
public class TradeResolver implements GraphQLResolver<Trade> {
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
	private WardRepository wardRepository;
	@Autowired
	private DetailsRepository detailsRepository;
	@Autowired
	private RealImageRepository realImageRepository;

	public Optional<User> getAuthor(Trade trade) {
		return userRepository.findById(trade.getUser().getId());
	}

	public Optional<RealEstateKind> getRealEstateKind(Trade trade) {
		return realEstateKindRepository.findById(trade.getRealEstateKind().getId());
	}

	public Optional<TradeKind> getTradeKind(Trade trade) {
		return tradeKindRepository.findById(trade.getTradeKind().getId());
	}

	public String getAddress(Trade trade) {
		Optional<Address> address = addressRepository.findById(trade.getAddress().getId());
		address.orElseThrow(() -> new NotFoundException("Cannot find Address Id = " + trade.getAddress().getId()));

		Optional<Ward> ward = wardRepository.findById(address.get().getWard());
		ward.orElseThrow(() -> new NotFoundException("Cannot find Ward Id = " + address.get().getWard()));

		String result = address.get().getDetail() + ", " + ward.get().getPathWithType();
		return result;
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