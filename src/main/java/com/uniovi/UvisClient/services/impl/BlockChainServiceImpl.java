package com.uniovi.UvisClient.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;
import com.uniovi.UvisClient.services.BlockChainService;

@Service
public class BlockChainServiceImpl implements BlockChainService {
	
	public static final String SESSION_DTO = "sessionChain";

	@Override
	public List<TransactionDto> getPendingTransactions(User user) {
		List<TransactionDto> pendingTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> pendingTransactions.addAll(BlockChain.getInstance().getPendingTransactionsByAddress(x.getAddress())));
		return pendingTransactions;
	}
	
	@Override
	public List<TransactionDto> getSentTransactions(User user) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> sentTransactions.addAll(BlockChain.getInstance().getSentTransactionsByAddress(x.getAddress())));
		return sentTransactions;
	}

	@Override
	public List<TransactionDto> getReceivedTransactions(User user) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> sentTransactions.addAll(BlockChain.getInstance().getReceivedTransactionsByAddress(x.getAddress())));
		return sentTransactions;
	}

	@Override
	public WalletDto getWalletByAddress(String address) {
		return BlockChain.getInstance().getWallet(address);
	}

	@Override
	public double getFundsByAddress(String address) {
		return BlockChain.getInstance().getBalance(address);
	}

	@Override
	public int getNumberOfBlocks() {
		return BlockChain.getInstance().getChainSize();
	}
	
}
