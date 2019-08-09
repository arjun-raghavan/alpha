/**
 * 
 */
package com.perseverance.authorization.exceptions;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author AR350758 AuthorizationControllerExceptionHandler.java Aug 8, 2019
 *         2:15:32 PM
 */
@ControllerAdvice
public class AuthorizationControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		return exceptionResponse(new ExceptionResponse(ex.getMessage(), request.getDescription(true)),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidRoleException.class, UnknownException.class })
	public ResponseEntity<Object> handleInvalidRoleException(UserNotFoundException ex, WebRequest request) {
		return exceptionResponse(new ExceptionResponse(ex.getMessage(), request.getDescription(true)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ InvalidArgumentsException.class })
	public ResponseEntity<Object> handleInvalidArgumentsException(UserNotFoundException ex, WebRequest request) {
		return exceptionResponse(new ExceptionResponse(ex.getMessage(), request.getDescription(true)),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		ObjectError objectError = allErrors.get(0);
		return exceptionResponse(new ExceptionResponse(objectError.getDefaultMessage(), request.getDescription(true)),
				HttpStatus.BAD_REQUEST);

	}

	private ResponseEntity<Object> exceptionResponse(ExceptionResponse exceptionResponse, HttpStatus status) {
		return ResponseEntity.status(status).body(exceptionResponse);
	}
}
