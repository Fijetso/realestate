package vn.edu.uit.realestate.Service.EntityService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.ExceptionHandler.ExistContentException;
import vn.edu.uit.realestate.ExceptionHandler.NotFoundException;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Repository.TradeKindRepository;
import vn.edu.uit.realestate.Service.IEntityService;

@Service
public class TradeKindService implements IEntityService {

	@Autowired
	private TradeKindRepository tradeKindRepository;

	@Override
	public List<TradeKind> findAll() {
		List<TradeKind> tradeKinds = tradeKindRepository.findAll();
		if (tradeKinds.isEmpty() == true) {
			throw new NotFoundException("Cannot find any Trade");
		}
		return tradeKinds;
	}

	@Override
	public TradeKind findById(Long id) {
		Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(id);
		if (foundTradeKind.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade Kind with id=" + id);
		}
		return foundTradeKind.get();
	}

	public void save(TradeKind tradeKind) {
		tradeKindRepository.save(tradeKind);
	}

	@Override
	public void deleteById(Long id) {
		Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(id);
		if (foundTradeKind.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade Kind with id=" + id);
		}
		if (foundTradeKind.get().getTrades().isEmpty() == false) {
			throw new ExistContentException(
					"There still exist 'Trade' in this Trade Kind. You should delete all these Trades before delete.");
		}
		tradeKindRepository.deleteById(id);
	}

	public MappingJacksonValue getTradesByTradeKindId(Long tradeKindId) {
		Optional<TradeKind> foundTradeKind = tradeKindRepository.findById(tradeKindId);
		if (foundTradeKind.isPresent() == false) {
			throw new NotFoundException("Cannot find any Trade Kind with id=" + tradeKindId);
		}
		List<Trade> trades = foundTradeKind.get().getTrades();
		if (trades.isEmpty() == true)
			throw new NotFoundException("Cannot find any Trade with Trade Kind Id=" + tradeKindId);
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password");
		SimpleBeanPropertyFilter addressAndDetailFilter = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("tradeKind");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", addressAndDetailFilter).addFilter("DetailsFilter", addressAndDetailFilter)
				.addFilter("TradeFilter", filterTrade);
		MappingJacksonValue mapping = new MappingJacksonValue(trades);
		mapping.setFilters(filters);
		return mapping;
	}
}
