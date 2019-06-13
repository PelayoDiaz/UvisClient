package com.uniovi.UvisClient.services;

import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;

import com.uniovi.UvisClient.communication.Connection;
import com.uniovi.UvisClient.entities.dto.AbstractDto;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;

@Service
public class BlockChainService {
	
	public static final String SESSION_DTO = "sessionChain";
	public static final String LISTENER = "/chain/sendChain";
	
	@Autowired
	private HttpSession httpSession;
	
	public void setBlockChain(BlockChainDto dto) {
		httpSession.setAttribute(SESSION_DTO, dto);
	}
	
	public void send(AbstractDto dto, String url, StompSessionHandlerAdapter handler, String listener) {
		try {
			StompSession session = Connection.initialize(url, handler);
			session.send(listener, dto);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
