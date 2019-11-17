package com.uniovi.UvisClient.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.services.impl.WalletServiceImpl;
import com.uniovi.UvisClient.services.security.SecurityService;

/**
 * Validator for the create wallet form.
 * It checks if the data introduced is correct.
 * 
 * @author Pelayo Díaz Soto
 *
 */
@Component
public class WalletFormValidator implements Validator {

	@Autowired
	private WalletServiceImpl walletService;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Wallet.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.empty");
		Wallet wallet = (Wallet) target;
		String loggedUsername = securityService.findLoggedInUsername();
		
		if (wallet.getName().length() < 4 || wallet.getName().length() > 25) {
			errors.rejectValue("name", "error.wallet.name.length");
		}
		
		if (this.walletService.getWalletByNameAndUsername(wallet.getName(), loggedUsername) != null) {
			errors.rejectValue("name", "error.wallet.name.duplicated");
		}
		
	}

}
