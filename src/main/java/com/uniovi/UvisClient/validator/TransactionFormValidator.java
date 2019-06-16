package com.uniovi.UvisClient.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.services.impl.BlockChainServiceImpl;
import com.uniovi.UvisClient.services.impl.WalletServiceImpl;

@Component
public class TransactionFormValidator implements Validator {
	
	@Autowired
	private WalletServiceImpl walletService;
	
	@Autowired
	private BlockChainServiceImpl chainService;

	@Override
	public boolean supports(Class<?> aClass) {
		return TransactionDto.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TransactionDto transaction = (TransactionDto) target;
		
//		checkFunds(transaction);
		checkReceiver(transaction, errors);
	}
	
	/**
	 * Checks if the receiver is valid. For a receiver to be valid, it has to exists 
	 * its address in the blockchain and in the data base.
	 * 
	 * @param transaction
	 * 			The transaction to be checked.
	 * @param errors
	 * 			Errors
	 */
	private void checkReceiver(TransactionDto transaction, Errors errors) {
		if (this.chainService.getWalletByAddress(transaction.receiver) == null) {
			errors.rejectValue("receiver", "error.transaction.receiver.no.exists");
		} else if (this.walletService.getWalletByAddress(transaction.receiver) == null) {
			errors.rejectValue("receiver", "error.transaction.receiver.no.exists");
		}
	}

}
