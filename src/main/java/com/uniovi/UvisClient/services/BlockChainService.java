package com.uniovi.UvisClient.services;

import java.util.List;

import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.dto.AbstractDto;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;

public interface BlockChainService {

	/**
	 * Method to communicate with the blockchain protocol where the chain is stored.
	 * 
	 * @param dto
	 * 			The information in a dto to send
	 * @param url
	 * 			The url of the node to connect
	 * @param handler
	 * 			The handler of the sessions established
	 * @param listener
	 * 			The listener where the node is listening
	 */
	public void send(AbstractDto dto, String url, StompSessionHandlerAdapter handler, String listener);
	
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

}
