package com.uniovi.UvisClient.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.UvisClient.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
