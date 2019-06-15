package com.uniovi.UvisClient.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.repositories.WalletRepository;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.util.CryptoUtil;

@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public Wallet getWalletByNameAndUsername(String name, String username) {
		return this.walletRepository.findByNameAndUserUsername(name, username);
	}
	
	@Override
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
