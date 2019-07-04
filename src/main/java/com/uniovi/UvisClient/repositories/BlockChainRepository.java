package com.uniovi.UvisClient.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.entities.dto.Node;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;

public class BlockChainRepository {
	
	/** The unique Blockchain to be instantiated. */
	private static BlockChainRepository singleChain;
	
	/** The dto which contains all the information of the chain */
	private BlockChainDto blockChainDto;
	
	/** The actual node the application communicates with. */ 
	private Node actualNode;
	
	public static BlockChainRepository getInstance() {
		if (singleChain == null) {
			singleChain = new BlockChainRepository();
		}
		return singleChain;
	}
	
	private BlockChainRepository() {}
	
	/**
	 * Updates the content of the chain.
	 * 
	 * @param dto
	 * 			The BlockChainDto
	 */
	public void update(BlockChainDto dto) {
		if (dto != null) {
			this.blockChainDto = dto;
		}
	}

	/**
	 * @return the blockChainDto
	 */
	public BlockChainDto getBlockChainDto() {
		return blockChainDto;
	}
	
	/**
	 * Returns the actual number of connected nodes in the chain.
	 * 
	 * @return Integer
	 * 			The number of connected nodes in the chain
	 */
	public int getConnectedNodes() {
		return this.blockChainDto.nodes.size();
	}
	
	/**
	 * Returns the list of wallets contained into the chain.
	 * 
	 * @return List<WalletDto>
	 * 			The list of wallets
	 */
	public List<WalletDto> getWalletsList() {
		return new ArrayList<WalletDto>(this.blockChainDto.wallets);
	}
	
	/**
	 * Returns all the transactions pending to be processed.
	 * 
	 * @return List<TransactionDto>
	 * 			The list of transactions.
	 */
	public List<TransactionDto> getPendingTransactionsList() {
		return new ArrayList<TransactionDto>(this.blockChainDto.transactions);
	}
	
	/**
	 * Returns all the transactions sent by a wallet pending to be processed.
	 * 
	 * @param address
	 * 			The address of the wallet which sends the transactions.
	 * 
	 * @return List<TransactionDto>
	 * 			The list of transactions.
	 */
	public List<TransactionDto> getPendingTransactionsByAddress(String address) {
		List<TransactionDto> pendingTransactions = this.blockChainDto.transactions.stream()
				.filter(x -> x.senderAddress.equals(address)).collect(Collectors.toList());
		return pendingTransactions;
	}
	
	/**
	 * Returns all the processed transactions sent by a wallet.
	 * 
	 * @param address
	 * 			The address of the wallet which sends the transactions.
	 * @return List<TransactionDto>
	 * 			The list of transactions
	 */
	public List<TransactionDto> getSentTransactionsByAddress(String address) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		this.blockChainDto.chain.forEach(x -> sentTransactions.addAll(x.transactions.stream()
				.filter(y -> y.senderAddress.equals(address)).collect(Collectors.toList())));
		return sentTransactions;
	}
	
	/**
	 * Returns all the received transactions by a wallet.
	 * 
	 * @param address
	 * 			The address of the wallet which recieves the transactions.
	 * @return List<TransactionDto>
	 * 			The list of transactions
	 */
	public List<TransactionDto> getReceivedTransactionsByAddress(String address) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		this.blockChainDto.chain.forEach(x -> sentTransactions.addAll(x.transactions.stream()
				.filter(y -> y.receiver.equals(address)).collect(Collectors.toList())));
		return sentTransactions;
	}
	
	/** 
	 * Returns a wallet by its address or null iif it doesn't exist.
	 * 
	 * @param address
	 * 			The address of the wallet to search.
	 * @return WalletDto
	 * 			The wallet. 
	 */
	public WalletDto getWallet(String address) {
		return this.blockChainDto.wallets.stream()
				.filter(x -> x.address.equals(address)).findFirst()
				.orElse(null);
	}

	/**
	 * @return the actualNode
	 */
	public Node getActualNode() {
		return actualNode;
	}
	
	/**
	 * @param actualNode the actualNode to set
	 */
	public void setActualNode(Node actualNode) {
		this.actualNode = actualNode;
	}

	/**
	 * Searches the next node which contains the blockchain to connect with. If there is
	 * no more nodes available it returns the actual node.
	 * 
	 * @return Node
	 * 			The next node in the net.
	 */
	public Node getNextNode() {
		BlockChainRepository.getInstance().blockChainDto.nodes.remove(actualNode);
		Node nextNode = this.blockChainDto.nodes.stream()
				.filter(x -> !x.getUrl().equals(actualNode.getUrl())).findFirst()
				.orElse(actualNode);
		this.actualNode = nextNode;
		return nextNode;
	}
	
	/**
	 * Returns all the nodes connected to the chain.
	 * 
	 * @return List<Node>
	 * 			All the nodes
	 */
	public List<Node> getNodes() {
		return (this.blockChainDto==null) ? null : new ArrayList<Node>(this.blockChainDto.nodes);
	}
	
	/**
	 * Returns the balance of an actual wallet given by address.
	 * 
	 * @param address
	 * 			The address of the wallet to check the balance.
	 * @return double
	 * 			The total amount of money available
	 */
	public double getBalance(String address) {
		List<TransactionDto> sentTransactions = this.getSentTransactionsByAddress(address);
		List<TransactionDto> receivedTransactions = this.getReceivedTransactionsByAddress(address);
		
		Double sent = sentTransactions.stream().mapToDouble(x -> x.amount).sum();
		Double received = receivedTransactions.stream().mapToDouble(x -> x.amount).sum();
		
		return received-sent;
	}
	
	/**
	 * Returns the actual number of blocks contained in the chain.
	 * 
	 * @return int
	 * 			The number of blocks contained in the chain.
	 */
	public int getChainSize() {
		return this.blockChainDto.chain.size();
	}

}
