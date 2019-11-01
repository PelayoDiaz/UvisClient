package com.uniovi.UvisClient.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Sender;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.repositories.BlockChainRepository;
import com.uniovi.UvisClient.repositories.WalletRepository;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.util.CryptoUtil;
import com.uniovi.UvisClient.util.DtoConverter;

@Service
public class WalletServiceImpl implements WalletService {
	
	public static final String CREATE_WALLET_LISTENER = "/app/chain/createWallet";
	
	public static final String CREATE_TRANSACTION_LISTENER = "/app/chain/createTransaction";
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public Wallet getWalletByNameAndUsername(String name, String username) {
		return this.walletRepository.findByNameAndUserUsername(name, username);
	}
	
	@Override
	public void createWallet(Wallet wallet) {
		String address = CryptoUtil.getSha256Hash(
				wallet.getUser().getUsername() + 
				wallet.getName() + 
				wallet.getUser().getName() + 
				wallet.getUser().getSurname1() + 
				wallet.getUser().getSurname2() +
				new Date().getTime());
		wallet.setAddress(address);
		Sender sender = new Sender(DtoConverter.toDto(wallet), BlockChainRepository.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), CREATE_WALLET_LISTENER);
		sender.start();
		this.walletRepository.save(wallet);
	}

	@Override
	public Wallet getWalletByAddress(String address) {
		return this.walletRepository.findByAddress(address);
	}
	
	@Override
	public List<Wallet> findAllWallets() {
		List<Wallet> wallets = new ArrayList<Wallet>();
		this.walletRepository.findAll().forEach(wallets::add);
		return wallets;
	}

	@Override
	public void sendFunds(TransactionDto transactionDto) {
		Sender sender = new Sender(transactionDto, BlockChainRepository.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), CREATE_TRANSACTION_LISTENER);
		sender.start();
		//We stop for one second to give the application time to update the chain.
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
