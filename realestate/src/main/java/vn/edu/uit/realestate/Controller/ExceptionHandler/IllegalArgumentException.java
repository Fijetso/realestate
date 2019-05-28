package vn.edu.uit.realestate.Controller.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {

	public IllegalArgumentException(String message) {
		super(message);
	}

}
