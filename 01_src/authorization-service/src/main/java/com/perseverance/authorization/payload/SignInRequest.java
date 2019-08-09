/**
 * 
 */
package com.perseverance.authorization.payload;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AR350758 SignInRequest.java Aug 9, 2019 12:43:03 PM
 */
@Getter @Setter
@NoArgsConstructor
public class SignInRequest implements Serializable{

	private static final long serialVersionUID = 6324944449507954267L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	/**
	 * @param username
	 * @param password
	 */
	public SignInRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
