/**
 * 
 */
package com.perseverance.authorization.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.perseverance.authorization.model.User;
import com.perseverance.authorization.repositories.UserRepository;

/**
 * @author AR350758 JwtUserDetailsService.java Aug 6, 2019 11:54:58 AM
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;

	@Autowired
	public JwtUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email : " + username));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

}
