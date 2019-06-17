package com.uniovi.UvisClient.communication;

import java.util.concurrent.ExecutionException;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.uniovi.UvisClient.entities.dto.AbstractDto;

public class Sender extends Thread {
		
	private AbstractDto dto;
	private String url;
	private StompSessionHandlerAdapter handler;
	private String listener;
	
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
		this.url = url;
		this.handler = handler;
		this.listener = listener;
		if (session==null) {
			try {
				session =Connection.initialize(url, handler);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Sender(Sender sender) {
		this(sender.dto, sender.url, sender.handler, sender.listener);
	}
	
	public void run() {
//		StompSession session = null;
		try {
			synchronized (session) {
				session.send(listener, dto);
			}
			
//		} catch (InterruptedException e) {
//			System.out.println("Por aquí");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			System.out.println("Por ahí");
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IllegalStateException e) {
			System.out.println("============================Criminal, cri cri criminal================================");
		} catch (Exception e) {
			System.out.println("============================Salté================================");
			if (session!=null) {
				session.disconnect();
			}
			Sender sender = new Sender(this);
			sender.start();
		}
	}
	

}
