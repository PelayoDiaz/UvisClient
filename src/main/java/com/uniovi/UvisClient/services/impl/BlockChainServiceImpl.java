package com.uniovi.UvisClient.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;
import com.uniovi.UvisClient.repositories.BlockChainRepository;
import com.uniovi.UvisClient.services.BlockChainService;

@Service
public class BlockChainServiceImpl implements BlockChainService {
	
	@Override
	public List<TransactionDto> getPendingTransactions(User user) {
		List<TransactionDto> pendingTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> pendingTransactions.addAll(BlockChainRepository.getInstance().getPendingTransactionsByAddress(x.getAddress())));
		return pendingTransactions;
	}
	
	@Override
	public List<TransactionDto> getSentTransactions(User user) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> sentTransactions.addAll(BlockChainRepository.getInstance().getSentTransactionsByAddress(x.getAddress())));
		return sentTransactions;
	}

	@Override
	public List<TransactionDto> getReceivedTransactions(User user) {
		List<TransactionDto> sentTransactions = new ArrayList<TransactionDto>();
		user.getWallets().forEach(x -> sentTransactions.addAll(BlockChainRepository.getInstance().getReceivedTransactionsByAddress(x.getAddress())));
		return sentTransactions;
	}

	@Override
	public WalletDto getWalletByAddress(String address) {
		return BlockChainRepository.getInstance().getWallet(address);
	}

	@Override
	public double getBalanceByAddress(String address) {
		return BlockChainRepository.getInstance().getBalance(address);
	}

	@Override
	public int getNumberOfBlocks() {
		return BlockChainRepository.getInstance().getChainSize();
	}
	
	@Override
	public List<TransactionDto> getPendingTransactions() {
		return BlockChainRepository.getInstance().getPendingTransactionsList();
	}
	
	@Override
	public int getTotalOfProcessedTransactions() {
		return BlockChainRepository.getInstance().getTotalOfProcessedTransactions();
	}
	
}
