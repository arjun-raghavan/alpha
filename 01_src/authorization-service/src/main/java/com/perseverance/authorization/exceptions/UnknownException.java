/**
 * 
 */
package com.perseverance.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author AR350758 UnknownException.java Aug 8, 2019 2:08:34 PM
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnknownException extends RuntimeException {

	private static final long serialVersionUID = -5038454248148050961L;

	public UnknownException(String message) {
		super(message);
	}
}
