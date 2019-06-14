package com.uniovi.UvisClient.entities;

import java.util.ArrayList;
import java.util.List;

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

}
