package vn.edu.uit.realestate.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Service.EntityService.TradeService;

@RestController
public class AdminTradeController {
	@Autowired
	private TradeService tradeService;

	@GetMapping("/trades/{id}/status/{status}")
	public ResponseEntity<MappingJacksonValue> updateTradeStatus(@PathVariable long id,
			@PathVariable String status) {
		MappingJacksonValue updatedTrade = tradeService.updateTradeStatus(id, status);
		return new ResponseEntity<>(updatedTrade, HttpStatus.OK);
	}
}