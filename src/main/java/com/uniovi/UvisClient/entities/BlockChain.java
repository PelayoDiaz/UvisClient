package com.uniovi.UvisClient.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;

public class BlockChain {
	
	/** The unique Blockchain to be instantiated. */
	private static BlockChain singleChain;
	
	private BlockChainDto blockChainDto;
	
	public static BlockChain getInstance() {
		if (singleChain == null) {
			singleChain = new BlockChain();
		}
		return singleChain;
	}
	
	private BlockChain() {}
	
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
	 * Returns the actual number of conected nodes in the chain.
	 * 
	 * @return Integer
	 * 			The number of conected nodes in the chain
	 */
	public int getConectedNodes() {
		return this.blockChainDto.nodes.size();
	}
	
	public List<WalletDto> getWalletsList() {
		return new ArrayList<WalletDto>(this.blockChainDto.wallets);
	}
	
	public List<TransactionDto> getTransactionsList() {
		return new ArrayList<TransactionDto>(this.blockChainDto.transactions);
	}
	
	public List<TransactionDto> getSentTransactionsByAddress(String address) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		this.blockChainDto.chain.forEach(x -> sentTransactions.addAll(x.transactions.stream()
				.filter(y -> y.senderAddress.equals(address)).collect(Collectors.toList())));
		return sentTransactions;
	}
	
	public List<TransactionDto> getReceivedTransactionsByAddress(String address) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		this.blockChainDto.chain.forEach(x -> sentTransactions.addAll(x.transactions.stream()
				.filter(y -> y.receiver.equals(address)).collect(Collectors.toList())));
		return sentTransactions;
	}
	
	public WalletDto getWallet(String address) {
		return this.blockChainDto.wallets.stream()
				.filter(x -> x.address.equals(address)).findFirst()
				.orElse(null);
	}

}
