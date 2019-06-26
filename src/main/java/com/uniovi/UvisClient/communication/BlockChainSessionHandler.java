package com.uniovi.UvisClient.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.repositories.BlockChainRepository;

import java.lang.reflect.Type;

public class BlockChainSessionHandler extends StompSessionHandlerAdapter {

	private Logger logger = LogManager.getLogger(BlockChainSessionHandler.class);
	
	private StompSession session;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/blockchainClient", this);
        logger.info("Subscribed to /topic/blockchainClient");
        this.session = session;
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Ups. This is embarrasing. Something went wrong while sending the object!", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return BlockChainDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    	
    	super.handleFrame(headers, payload);
    	BlockChainDto chain = (BlockChainDto) payload;
    	if (chain != null) {
    		BlockChainRepository.getInstance().update(chain);
    		logger.info("Chain obtained successfully. Ready to make operations!");
    	} else {
    		logger.info("Something went wrong while getting de chain.");
    	}
//    	this.session.disconnect();
    	
    }

}
