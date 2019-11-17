package com.uniovi.UvisClient.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.UvisClient.entities.Wallet;

/**
 * Repository to obtain the information from the Wallet table.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public interface WalletRepository extends CrudRepository<Wallet, Long> {

	/**
	 * Finds a wallet by its name and username to which belongs to.
	 * 
	 * @param name
	 * 			The name of the wallet.
	 * @param username
	 * 			The username's owner.
	 * @return Wallet
	 * 			The wallet found.
	 */
	Wallet findByNameAndUserUsername(String name, String username);
	
	/**
	 * Finds a wallet by its address.
	 * 
	 * @param address
	 * 			The address of the wallet.
	 * @return Wallet
	 * 			The wallet found.
	 */
	Wallet findByAddress(String address);
}
