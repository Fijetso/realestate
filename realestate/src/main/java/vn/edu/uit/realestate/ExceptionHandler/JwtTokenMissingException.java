package vn.edu.uit.realestate.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class JwtTokenMissingException extends RuntimeException{
	public JwtTokenMissingException(String message) {
		super(message);
	}
}
