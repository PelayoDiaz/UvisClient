package com.uniovi.UvisClient.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.UvisClient.entities.Wallet;

public interface WalletRepository extends CrudRepository<Wallet, Long> {

	Wallet findByNameAndUserUsername(String name, String username);
	
	Wallet findByAddress(String address);

}
