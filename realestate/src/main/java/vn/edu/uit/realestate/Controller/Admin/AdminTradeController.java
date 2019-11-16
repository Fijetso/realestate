package vn.edu.uit.realestate.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.uit.realestate.Service.EntityService.TradeService;

@RestController
@RequestMapping("/secured/admin")
public class AdminTradeController {
	@Autowired
	private TradeService tradeService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/trades/{id}")
	public ResponseEntity<MappingJacksonValue> updateTradeStatus(@PathVariable long id,
			@RequestParam(value = "status", required = true) String status) {
		MappingJacksonValue updatedTrade = tradeService.updateTradeStatus(id, status);
		return new ResponseEntity<>(updatedTrade, HttpStatus.OK);
	}	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/trades")
	public ResponseEntity<String> demo() {
		return new ResponseEntity<>("Hello from AdminTradeController", HttpStatus.OK);
	}
}