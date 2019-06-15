package com.uniovi.UvisClient.entities.dto;

import java.io.Serializable;
import java.util.List;

public class TransactionDto extends AbstractDto implements Serializable {
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -2161764576648183045L;
	
	public String id;
	public byte[] sender;
	public String senderAddress;
	public String receiver;
	public double amount;
	public byte[] signature;
	public long timeStamp;
	public List<TransactionInputDto> inputs;
	public List<TransactionOutputDto> outputs;
	
	/**
	 * @return the senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}
	/**
	 * @param senderAddress the senderAddress to set
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	/**
	 * @param receiver the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
