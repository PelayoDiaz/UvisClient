package com.uniovi.UvisClient.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to access session information.
 * 
 * @author Pelayo Díaz Soto
 *
 */
@Service
public class SecurityService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

	/**
	 * Returns the username of the logged user.
	 * 
	 * @return String
	 * 			The username of the logged user
	 */
	public String findLoggedInUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return username;
	}
	
	/**
	 * Makes a login when an user completes successfully the sign up form.
	 * 
	 * @param username
	 * 			The username of the user to login.
	 * @param password
	 * 			The password of the user to login.
	 */
	public void autoLogin(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
		authenticationManager.authenticate(aToken);
		if (aToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(aToken);
			LOGGER.debug(String.format("Auto login %s successfully!", username));
		}
	}

}
