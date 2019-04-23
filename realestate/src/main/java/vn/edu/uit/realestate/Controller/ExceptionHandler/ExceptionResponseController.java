package vn.edu.uit.realestate.Controller.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import vn.edu.uit.realestate.Model.ExceptionResponse;
import vn.edu.uit.realestate.Controller.ExceptionHandler.*;

@ControllerAdvice
@RestController
public class ExceptionResponseController extends ResponseEntityExceptionHandler {

	public ExceptionResponseController() {
		// TODO Auto-generated constructor stub
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getBindingResult().toString(),"Validation Failed");
				return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}
