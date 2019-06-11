package com.uniovi.UvisClient.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UserService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("pedrods", "Pedro", "Díaz", "Suarez");
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
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}

}
