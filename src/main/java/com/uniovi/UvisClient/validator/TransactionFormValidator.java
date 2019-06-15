package com.uniovi.UvisClient.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.services.impl.WalletServiceImpl;
import com.uniovi.UvisClient.services.security.SecurityService;

@Component
public class TransactionFormValidator implements Validator {

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
	
	private void checkReceiver(TransactionDto transaction, Errors errors) {
		
	}

}
