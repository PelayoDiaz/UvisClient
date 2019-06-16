package com.uniovi.UvisClient.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.communication.Connection;
import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.dto.AbstractDto;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.entities.dto.WalletDto;
import com.uniovi.UvisClient.services.BlockChainService;

@Service
public class BlockChainServiceImpl implements BlockChainService {
	
	public static final String SESSION_DTO = "sessionChain";
	public static final String LISTENER = "/chain/sendChain";
	
	@Autowired
	private HttpSession httpSession;
	
	public void setBlockChain(BlockChainDto dto) {
		httpSession.setAttribute(SESSION_DTO, dto);
	}
	
	@Override
	public synchronized void send(AbstractDto dto, String url, StompSessionHandlerAdapter handler, String listener) {
		try {
			StompSession session = Connection.initialize(url, handler);
//			synchronized(this) {
				session.send(listener, dto);
//			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
