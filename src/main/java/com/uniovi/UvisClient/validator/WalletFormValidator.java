package com.uniovi.UvisClient.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.services.security.SecurityService;

@Component
public class WalletFormValidator implements Validator {

	@Autowired
	private WalletService walletService;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Wallet.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Wallet wallet = (Wallet) target;
		String loggedUsername = securityService.findLoggedInUsername();
		
		if (wallet.getName().length() < 5 || wallet.getName().length() > 25) {
			errors.rejectValue("name", "error.wallet.name.length");
		}
		
		if (this.walletService.getWalletByNameAndUsername(wallet.getName(), loggedUsername) != null) {
			errors.rejectValue("name", "error.wallet.name.duplicated");
		}
		
	}

}
