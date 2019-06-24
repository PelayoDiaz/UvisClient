package com.uniovi.UvisClient.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Sender;
import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.repositories.WalletRepository;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.util.CryptoUtil;
import com.uniovi.UvisClient.util.DtoConverter;

@Service
public class WalletServiceImpl implements WalletService {
	
	public static final String LISTENER = "/app/chain/createWallet";
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public Wallet getWalletByNameAndUsername(String name, String username) {
		return this.walletRepository.findByNameAndUserUsername(name, username);
	}
	
	@Override
	public void addWallet(Wallet wallet) {
		String address = CryptoUtil.getSha256Hash(
				wallet.getUser().getUsername() + 
				wallet.getName() + 
				wallet.getUser().getName() + 
				wallet.getUser().getSurname1() + 
				wallet.getUser().getSurname2());
		wallet.setAddress(address);
		Sender sender = new Sender(DtoConverter.toDto(wallet), BlockChain.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), LISTENER);
		sender.start();
		this.walletRepository.save(wallet);
	}

	@Override
	public Wallet getWalletByAddress(String address) {
		return this.walletRepository.findByAddress(address);
	}

}
