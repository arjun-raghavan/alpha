/**
 * 
 */
package com.perseverance.authorization.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author AR350758 SignUpRequest.java Aug 8, 2019 12:01:00 PM
 */
@Getter
@Setter
public class SignUpRequest {

	@NotBlank
	@Size(min = 4, max = 40, message="Name should have min of 4 and max of 40 characters")
	private String name;

	@NotBlank
	@Size(min = 3, max = 15, message="Username should have min of 3 and max of 15 characters")
	private String username;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;
}
