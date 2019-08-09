/**
 * 
 */
package com.perseverance.authorization.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perseverance.authorization.auth.JwtTokenUtil;
import com.perseverance.authorization.payload.JwtResponse;
import com.perseverance.authorization.payload.SignInRequest;
import com.perseverance.authorization.payload.SignUpRequest;
import com.perseverance.authorization.service.UserService;

/**
 * @author AR350758 AuthorizationController.java Aug 8, 2019 11:52:28 AM
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) throws Exception {
		final UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
		authenticate(signInRequest.getUsername(), signInRequest.getPassword());
		// final UserDetails userDetails =
		// userService.loadUserByUsername(signInRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, HttpServletRequest request)
			throws Exception {
		// TODO: Change the return to uri
		return new ResponseEntity(userService.registerUser(signUpRequest), HttpStatus.CREATED);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
