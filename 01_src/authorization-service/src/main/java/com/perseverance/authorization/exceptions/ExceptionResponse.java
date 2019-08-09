/**
 * 
 */
package com.perseverance.authorization.exceptions;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AR350758 ExceptionResponse.java Aug 7, 2019 11:54:39 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;
	private String message;
	private String details;

	/**
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ExceptionResponse(String message, String details) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.details = details;
	}

}
