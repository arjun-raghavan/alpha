/**
 * 
 */
package com.perseverance.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AR350758 InvalidArgumentsException.java Aug 8, 2019 2:02:33 PM
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends RuntimeException {

	private static final long serialVersionUID = -473247713053428534L;

	public InvalidArgumentsException(String message) {
		super(message);
	}
}
