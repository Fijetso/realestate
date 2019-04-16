package vn.edu.uit.realestate.Controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionResponseResource extends ResponseEntityExceptionHandler {

	public ExceptionResponseResource() {
		// TODO Auto-generated constructor stub
	}

}
