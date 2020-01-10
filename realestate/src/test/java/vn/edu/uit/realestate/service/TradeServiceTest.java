package vn.edu.uit.realestate.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import junit.framework.Assert;
import vn.edu.uit.realestate.Controller.TradeController;
import vn.edu.uit.realestate.Relational.Model.Address;
import vn.edu.uit.realestate.Relational.Model.Coordinate;
import vn.edu.uit.realestate.Relational.Model.Details;
import vn.edu.uit.realestate.Relational.Model.RealEstateKind;
import vn.edu.uit.realestate.Relational.Model.Trade;
import vn.edu.uit.realestate.Relational.Model.TradeKind;
import vn.edu.uit.realestate.Relational.Model.User;
import vn.edu.uit.realestate.Relational.Model.Enum.TradeStatus;
import vn.edu.uit.realestate.Relational.Repository.TradeRepository;
import vn.edu.uit.realestate.Service.EntityService.TradeService;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
public class TradeServiceTest {
	@TestConfiguration
	public static class TradeServiceTestConfiguration {
		@Bean
		TradeService tradeService() {
			return new TradeService();
		}
	}
	@MockBean
	TradeRepository tradeRepository;
	@Autowired
	TradeService tradeService;
	@Autowired
	MockMvc mockMvc;
	List<Trade> tradeList = new ArrayList<>();

	@Before
	public void setUp() {
		for (int i = 1; i < 5; i++) {
			Trade trade = new Trade();
			trade.setId((long) i);
			trade.setDescription("Mô tả của thông tin bất động sản " + i);
			trade.setCost((long) 500000);
			trade.setTradeStatus(TradeStatus.APPROVED);
			User user = new User("Nguyễn Thị Ngọc Huyền", "123456@gmail.com", "0123345678", null, true);
			user.setId((long) i);
			trade.setUser(user);
			Details details = new Details((long) 10 + i, (long) 10 + i, (long) 100 + i, "Hướng Tây Bắc", "1 trệt 1 lầu",
					"Sổ hồng chính chủ", 2, 2, "Các tiện nghi khác", "Các chi tiết khác");
			details.setId((long) i);
			trade.setDetails(details);
			Coordinate coordinate = new Coordinate(117, 156);
			coordinate.setId((long) i);
			RealEstateKind realEstateKind = new RealEstateKind((long) i, "Chung cư", null);
			trade.setRealEstateKind(realEstateKind);
			TradeKind tradeKind = new TradeKind((long) i, "Thuê", null);
			trade.setTradeKind(tradeKind);
			Address address = new Address("Số nhà 53", (long) 1788, (long) 174, (long) 79);
			address.setId((long) i);
			trade.setAddress(address);
			tradeList.add(trade);
		}
		Mockito.when(tradeRepository.findAll()).thenReturn(tradeList);
	}

	@SuppressWarnings("deprecation")
	@Test
		public void testFindAll() throws Exception {
		SimpleBeanPropertyFilter userFilter = SimpleBeanPropertyFilter.serializeAllExcept("trades", "password",
				"requests", "favoriteTrades", "roles");
		SimpleBeanPropertyFilter filterExceptTrade = SimpleBeanPropertyFilter.serializeAllExcept("trade");
		SimpleBeanPropertyFilter filterTrade = SimpleBeanPropertyFilter.serializeAllExcept("favoriteTrades");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", userFilter)
				.addFilter("AddressFilter", filterExceptTrade).addFilter("DetailsFilter", filterExceptTrade)
				.addFilter("TradeFilter", filterTrade);
		MappingJacksonValue expected = new MappingJacksonValue(tradeList);
		expected.setFilters(filters);
		Assert.assertEquals(expected, tradeService.findAll());
		}
}
