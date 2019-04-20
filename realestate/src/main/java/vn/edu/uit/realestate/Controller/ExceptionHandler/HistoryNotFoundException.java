package vn.edu.uit.realestate.Controller.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HistoryNotFoundException extends RuntimeException {

	public HistoryNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public HistoryNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
