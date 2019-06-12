package com.uniovi.UvisClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.repositories.WalletRepository;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository walletRepository;

	public Wallet getWalletByNameAndUsername(String name, String id) {
		return this.walletRepository.findByNameAndUserUsername(name, id);
	}

}
