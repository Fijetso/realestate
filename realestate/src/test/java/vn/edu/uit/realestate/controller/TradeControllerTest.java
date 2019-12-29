package vn.edu.uit.realestate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import vn.edu.uit.realestate.Controller.TradeController;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Service.EntityService.TradeService;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
public class TradeControllerTest {

	@Mock
	private TradeService tradeService;
	@InjectMocks
	TradeController tradeController;
	MockMvc mockMvc;
	MappingJacksonValue validTrade;
	Trade trade;
	@BeforeEach
	void setUp() {
		trade = new Trade();
		trade.setId((long) 7);
		trade.setDescription("Đây là mô tả của thông tin bất động sản");
		trade.setCost((long) 500000);
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password",
				"requests", "favoriteTrades", "roles");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("TradeFilter", filterTrade);
		validTrade = new MappingJacksonValue(trade);
		validTrade.setFilters(filters);
		mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build(); 
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void testFindById() throws Exception{
		given(tradeService.findById(any())).willReturn(validTrade);
		mockMvc.perform(get("/trades/" + trade.getId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(trade.getId().toString())))
		.andExpect(jsonPath("$.cost", is(trade.getCost().toString())));
	}
}
