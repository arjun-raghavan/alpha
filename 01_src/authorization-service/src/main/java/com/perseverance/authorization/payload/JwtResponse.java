/**
 * 
 */
package com.perseverance.authorization.payload;

import java.io.Serializable;

/**
 * @author AR350758 JwtResponse.java Aug 6, 2019 11:52:43 AM
 */
public class JwtResponse implements Serializable{

	private static final long serialVersionUID = 407254412306596954L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}
}
