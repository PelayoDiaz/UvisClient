package com.uniovi.UvisClient.util;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.WalletDto;

public class DtoConverter {
	
	public static WalletDto toDto(Wallet wallet) {
		WalletDto dto = new WalletDto();
		dto.address = wallet.getAddress();
		dto.name = wallet.getName();
		dto.username = wallet.getUser().getUsername();
		return dto;
	}

}
