package com.uniovi.UvisClient.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.services.impl.UserServiceImpl;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserServiceImpl usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("uo251000", "Pelayo", "Díaz", "Soto");
		user1.setPassword("123456");
		User user2 = new User("99999991B", "Lucas", "Núñez", "Soto");
		user2.setPassword("123456");
		User user3 = new User("9999992C", "María", "Rodríguez", "Martinez");
		user3.setPassword("123456");
		User user4 = new User("99999993D", "Marta", "Almonte", "Diego");
		user4.setPassword("123456");
		User user5 = new User("99999977E", "Pelayo", "Valdes", "Gonzalez");
		user5.setPassword("123456");
		User user6 = new User("99999988F", "Edward", "Núñez", "Fernandez");
		user6.setPassword("123456");
		
		usersService.createUser(user1);
		usersService.createUser(user2);
		usersService.createUser(user3);
		usersService.createUser(user4);
		usersService.createUser(user5);
		usersService.createUser(user6);
	}

}
