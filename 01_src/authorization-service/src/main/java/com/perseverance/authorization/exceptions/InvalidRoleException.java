/**
 * 
 */
package com.perseverance.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AR350758 ResourceNotFoundException.java Aug 3, 2019 9:52:22 PM
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 2020965021616803153L;

	public InvalidRoleException(String message) {
		super(message);
	}
}
