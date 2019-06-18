package com.uniovi.UvisClient.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.dto.AbstractDto;

public class Sender extends Thread {
	
	private Logger logger = LogManager.getLogger(Sender.class);
		
	private AbstractDto dto;
	private String listener;

	private Object url;
	
	public static StompSession session = null;

	/**
	 * Class to communicate with the blockchain protocol where the chain is stored.
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
	public Sender(AbstractDto dto, String url, StompSessionHandlerAdapter handler, String listener) {
		this.dto = dto;
		this.listener = listener;
		this.url = url;
		initSession(url, handler);
	}
	
	private void initSession(String url, StompSessionHandlerAdapter handler) {
		if (session == null && (BlockChain.getInstance().getNodes()==null || BlockChain.getInstance().getNodes().size()>0)) { //First Connection 
			session = Connection.initialize(url, handler);
		}
	}
	
	/**
	 * Communicates with the blockchain node's by sending a dto.
	 */
	public void run() {
		try {
			if (session!=null ) {
				synchronized (session) {
					session.send(listener, dto);
				}
			}
		} catch (IllegalStateException e) { //The node is not listening anymore, gets the next node and retries.
			logger.info("Connection with actual node lost: Searching a new node to communicate with");
			session = null;
			BlockChain.getInstance().getNextNode();
			if (!this.url.equals(BlockChain.getInstance().getActualNode().getUrl())) {
				Sender sender = new Sender(this.dto, BlockChain.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), this.listener);
				logger.info(String.format("The new node's url is: %s", BlockChain.getInstance().getActualNode().getUrl()));
				sender.start();
			} else {
				logger.error("Cannot find another node to connect with. Please contact with an administrator.");
			}
		}
	}
	

}
