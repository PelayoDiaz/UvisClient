package com.uniovi.UvisClient.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.uniovi.UvisClient.entities.dto.AbstractDto;

/**
 * It is responsible of updating the chain every 10 seconds for show updated 
 * info to the user.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public class Updater extends Sender {
	
	private Logger logger = LogManager.getLogger(Updater.class);

	/**
	 * Constructor.
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
	public Updater(AbstractDto dto, String url, StompSessionHandlerAdapter handler, String listener) {
		super(dto, url, handler, listener);
	}
	
	@Override
	public void run() {
		do {
			try {
				super.run();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				logger.error("Something went wrong while trying to update the chain.");
			}
		} while (true);
	}

}
