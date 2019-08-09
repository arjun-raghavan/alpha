/**
 * 
 */
package com.perseverance.authorization.service;

import java.util.ArrayList;
import java.util.Collections;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perseverance.authorization.exceptions.InvalidArgumentsException;
import com.perseverance.authorization.exceptions.InvalidRoleException;
import com.perseverance.authorization.exceptions.UnknownException;
import com.perseverance.authorization.model.Role;
import com.perseverance.authorization.model.RoleName;
import com.perseverance.authorization.model.User;
import com.perseverance.authorization.payload.SignUpRequest;
import com.perseverance.authorization.repositories.RoleRepository;
import com.perseverance.authorization.repositories.UserRepository;

/**
 * @author AR350758 UserService.java Aug 8, 2019 11:00:48 AM
 */
@Service
public class UserService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	/**
	 * @param userRepository
	 * @param roleRepository
	 * @param passwordEncoder
	 */
	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	/**
	 * @param signUpRequest
	 * @return
	 * @throws Exception
	 */
	public User registerUser(@Valid SignUpRequest signUpRequest) throws Exception {
		// Check if user exists by username
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new InvalidArgumentsException("Username exists");
		}

		// check if user exists by email
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new InvalidArgumentsException("Email exists");
		}
		// JPA checks doesn't give guarantee on uniqueness - concurrent transaction - so
		// add @Column(unique = true)

		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName(RoleName.ROLE_AGENT)
				.orElseThrow(() -> new InvalidRoleException("User Role not set."));
		user.setRoles(Collections.singleton(userRole));
		User newUser = userRepository.save(user);
		if (newUser != null) {
			return newUser;
		} else {
			throw new UnknownException("Failed to register user");
		}
	}

}
