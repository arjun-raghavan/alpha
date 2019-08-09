/**
 * 
 */
package com.perseverance.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AR350758 ResourceNotFoundException.java Aug 3, 2019 9:52:22 PM
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7939003260121027257L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
