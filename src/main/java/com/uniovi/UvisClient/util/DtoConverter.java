package com.uniovi.UvisClient.util;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.WalletDto;

/**
 * Class to convert enitites into DTOs.
 * 
 * @author Pelayo Díaz Soto
 *
 */
public class DtoConverter {
	
	/**
	 * Convert a wallet into its DTO.
	 * 
	 * @param wallet
	 * 			The wallet to convert.
	 * @return WalletDto
	 * 			The DTO equivalent.
	 */
	public static WalletDto toDto(Wallet wallet) {
		WalletDto dto = new WalletDto();
		dto.address = wallet.getAddress();
		dto.name = wallet.getName();
		dto.username = wallet.getUser().getUsername();
		return dto;
	}

}
