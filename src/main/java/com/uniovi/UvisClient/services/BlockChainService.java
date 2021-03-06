package com.uniovi.UvisClient.services;

import java.util.List;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;

/**
 * Interface to contain all the methods that they relate with the chain.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public interface BlockChainService {
	
	/**
	 * Returns a list of transactions which are pending of being processed.
	 *  
	 * @param user
	 * 			The user to search the transactions for.
	 * @return List<TransactionDto>
	 * 			The list of transactions.
	 */
	public List<TransactionDto> getPendingTransactions(User user);
	
	/**
	 * Returns a list of the processed transactions sent by a user.
	 * 
	 * @param user
	 * 			The user who sent the transactions
	 * @return List<TransactionDto> 
	 * 			The list of transactions sent by a user
	 */
	public List<TransactionDto> getSentTransactions(User user);
	
	/**
	 * Returns a list of the processed transactions received by a user.
	 * 
	 * @param user
	 * 			The user who receives the transactions
	 * @return List<TransactionDto> 
	 * 			The list of transactions received by a user
	 */
	public List<TransactionDto> getReceivedTransactions(User user);
	
	/**
	 * Returns a wallet by its address if it is contained into the blockchain.
	 * 
	 * @param address
	 * 			The address of the wallet
	 * @return WalletDto
	 * 			The found wallet
	 */
	public WalletDto getWalletByAddress(String address);
	
	/**
	 * Returns the actual funds of a wallet given by address.
	 * 
	 * @param address
	 * 			The address of the wallet to be checked.
	 * @return Double
	 * 			The funds available.
	 */
	public double getBalanceByAddress(String address);
	
	/**
	 * Returns the actual number of blocks contained in the chain.
	 * 
	 * @return int
	 * 			The number of blocks contained in the chain.
	 */
	public int getNumberOfBlocks();
	
	/**
	 * Returns all the transactions contained into the chain which are pending
	 * of being processed and included in a block.
	 * @return List<TransactionDto>
	 * 			The list of pending transactions
	 */
	public List<TransactionDto> getPendingTransactions();
	
	/**
	 * Returns the total of processed transactions in the chain.
	 * @return Integer
	 * 			The number of total transactions contained in all the blocks of the chain.
	 */
	public int getTotalOfProcessedTransactions();
	
	/**
	 * It sets all the funds to the wallets of a user by reading the values on the chain.
	 * 
	 * @param User
	 * 			The user who needs to update his wallets funds.
	 * 
	 * @return List<Wallet>
	 * 			The list of the user's wallets updated with the funds.
	 */
	public List<Wallet> updateFunds(User user);
	
	/**
	 * Returns the number of connected nodes into the chain.
	 * 
	 * @return int
	 * 			The number of connected nodes.
	 */
	public int getNumberOfConnectedNodes();

}
