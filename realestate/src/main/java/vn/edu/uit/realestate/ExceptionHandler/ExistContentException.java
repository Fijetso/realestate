package vn.edu.uit.realestate.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistContentException extends RuntimeException {

	public ExistContentException(String message) {
		super(message);
	}

}
