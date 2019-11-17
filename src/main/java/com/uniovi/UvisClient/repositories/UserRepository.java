package com.uniovi.UvisClient.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.UvisClient.entities.User;

/**
 * Repository to obtain the information from the Users table.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Finds a user by its username.
	 * 
	 * @param username
	 * 			The username to search the user with.
	 * @return User
	 * 			The found user.
	 */
	User findByUsername(String username);

}
