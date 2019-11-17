package com.uniovi.UvisClient.services;

import com.uniovi.UvisClient.entities.User;

/**
 * Interface to contain all the methods that they relate with the users.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public interface UserService {

	/**
	 * Adds a new User to the application.
	 * 
	 * @param user
	 * 			The new user to be added.
	 */
	public void createUser(User user);
	
	/**
	 * Finds users by their usernames.
	 * 
	 * @param username
	 * 			The username to search the user
	 * @return User
	 * 			The user found
	 */
	public User getUserByUsername(String username);
}
