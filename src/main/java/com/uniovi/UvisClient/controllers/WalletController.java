package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.validator.WalletFormValidator;

@Controller
public class WalletController {
	
	@Autowired
	private WalletFormValidator walletFormValidator;
	
	@Autowired
	private WalletService walletService;

	@RequestMapping(value = "/wallet/add", method = RequestMethod.GET)
	public String addWalletView(Model model) {
		model.addAttribute("wallet", new Wallet());
		return "wallet/create";
	}
	
	@RequestMapping(value = "/wallet", method = RequestMethod.POST)
	public String addWallet(@ModelAttribute("wallet") Wallet wallet, BindingResult result, Model model) {
		this.walletFormValidator.validate(wallet, result);
		if (result.hasErrors()) {
			return "wallet/create";
		}
		
//		this.walletService.addWallet();
		return "wallet/create";
	}

}
