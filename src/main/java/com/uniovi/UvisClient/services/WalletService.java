package com.uniovi.UvisClient.services;

import java.util.List;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;

/**
 * Interface to contain all the methods that they relate with the wallets.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public interface WalletService {

	/**
	 * Finds an existing wallet searching it by name and username.
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
	public void createWallet(Wallet wallet);
	
	/**
	 * Sends the transaction data to the blockchain to be created.
	 * 
	 * @param transactionDto
	 * 			The information of the transaction to be created.
	 */
	public void sendFunds(TransactionDto transactionDto);
	
	/**
	 * Searches a wallet by its address.
	 * 
	 * @param address
	 * 
	 * 
	 * @return Wallet
	 * 			The found wallet
	 */
	public Wallet getWalletByAddress(String address);
	
	/**
	 * Returns all the wallets contained in the data base
	 * @return List<Wallet>
	 * 			All the wallets contained in the list.
	 * 		
	 */
	public List<Wallet> findAllWallets();
}
