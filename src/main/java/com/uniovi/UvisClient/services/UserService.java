package com.uniovi.UvisClient.services;

import java.util.List;

import com.uniovi.UvisClient.entities.User;

public interface UserService {
	
	/**
	 * Returns a list of all the users in the chain.
	 * 
	 * @return List<User>
	 * 			The list of users in the application
	 */
	public List<User> getUsers();

	/**
	 * Adds a new User to the application.
	 * 
	 * @param user
	 * 			The new user to be added.
	 */
	public void addUser(User user);
	
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
