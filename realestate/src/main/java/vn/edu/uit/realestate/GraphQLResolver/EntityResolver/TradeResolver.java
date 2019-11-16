package vn.edu.uit.realestate.GraphQLResolver.EntityResolver;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import vn.edu.uit.realestate.ExceptionHandler.CustomGraphQLException;
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
import vn.edu.uit.realestate.Relational.Repository.AddressRepository;
import vn.edu.uit.realestate.Relational.Repository.BluePrintRepository;
import vn.edu.uit.realestate.Relational.Repository.CoordinateRepository;
import vn.edu.uit.realestate.Relational.Repository.DetailsRepository;
import vn.edu.uit.realestate.Relational.Repository.RealEstateKindRepository;
import vn.edu.uit.realestate.Relational.Repository.RealImageRepository;
import vn.edu.uit.realestate.Relational.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Relational.Repository.UserRepository;
import vn.edu.uit.realestate.Relational.Repository.AddressTree.WardRepository;

@Component
public class TradeResolver implements GraphQLResolver<Trade> {
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
	@Autowired
	private BluePrintRepository bluePrintRepository;
	@Autowired
	private CoordinateRepository coordinateRepository;

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
		address.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find Address Id = " + trade.getAddress().getId()));

		Optional<Ward> ward = wardRepository.findById(address.get().getWard());
		ward.orElseThrow(() -> new CustomGraphQLException(400,
				"Not Found Exception: Cannot find Ward Id = " + address.get().getWard()));

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
		List<BluePrint> result =  bluePrintRepository.findByTrade(trade);
		return result;
	}

	public Optional<Coordinate> getCoordinate(Trade trade) {
		return coordinateRepository.findById(trade.getCoordinate().getId());
	}
}
