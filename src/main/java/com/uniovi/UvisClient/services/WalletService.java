package com.uniovi.UvisClient.services;

import com.uniovi.UvisClient.entities.Wallet;

public interface WalletService {

	/**
	 * Find an existing wallet searching it by name and username.
	 * 
	 * @param name
	 * 			The wallet's anme given by the user.
	 * @param username
	 * 			The username
	 * 
	 * @return Wallet
	 * 			The finded wallet
	 */
	public Wallet getWalletByNameAndUsername(String name, String username);
	
	/**
	 * Stores a wallet into the data base and generates an address for it.
	 * 
	 * @param wallet
	 * 			The wallet to be stored.
	 */
	public void addWallet(Wallet wallet);
}
