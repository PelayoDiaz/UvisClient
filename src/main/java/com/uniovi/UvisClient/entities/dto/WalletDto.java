package com.uniovi.UvisClient.entities.dto;

import java.io.Serializable;

public class WalletDto extends AbstractDto implements Serializable{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 7621705334565136287L;
	
	public String name;
	public String address;
	public String username;
}
