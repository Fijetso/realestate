package vn.edu.uit.realestate.Service.EntityService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.DataAccess.RequestRepository;
import vn.edu.uit.realestate.DataAccess.TradeRepository;
import vn.edu.uit.realestate.Model.Request;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class RequestService implements IEntityService {
	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private RequestRepository requestRepository;
	

	@Override
	public Object findAll() {
		return null;
	}

	@Override
	public Request findById(Long id) {
		Optional<Request> foundRequest = requestRepository.findById(id);
		if (foundRequest.isPresent() == false) {
			throw new NotFoundException("Cannot find any Request with id=" + id);
		}
		return foundRequest.get();
	}

	@Override
	public void deleteById(Long id) {
		Optional<Request> foundRequest = requestRepository.findById(id);
		if (foundRequest.isPresent() == false) {
			throw new NotFoundException("Cannot find any Request with id=" + id);
		}
		requestRepository.deleteById(id);
	}

//	public MappingJacksonValue findAllTradeByUserId(Long userId) {
//		Optional<User> foundUser = userRepository.findById(userId);
//		if (foundUser.isPresent() == false) {
//			throw new NotFoundException("Cannot find any User with id=" + userId);
//		}
//		List<Trade> foundTradeList = foundUser.get().getTrades();
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("user");
//		FilterProvider filters = new SimpleFilterProvider().addFilter("TradeFilter", filter);
//		MappingJacksonValue mapping = new MappingJacksonValue(foundTradeList);
//		mapping.setFilters(filters);
//		return mapping;
//	}
}
