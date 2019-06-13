package com.uniovi.UvisClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.repositories.WalletRepository;
import com.uniovi.UvisClient.util.CryptoUtil;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository walletRepository;

	public Wallet getWalletByNameAndUsername(String name, String id) {
		return this.walletRepository.findByNameAndUserUsername(name, id);
	}
	
	public void addWallet(Wallet wallet) {
		String address = CryptoUtil.getSha256Hash(
				wallet.getUser().getUsername() + 
				wallet.getName() + 
				wallet.getUser().getName() + 
				wallet.getUser().getSurname1() + 
				wallet.getUser().getSurname2());
		wallet.setAddress(address);
		this.walletRepository.save(wallet);
	}

}
