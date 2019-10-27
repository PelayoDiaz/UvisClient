package com.uniovi.UvisClient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.services.BlockChainService;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.validator.WalletFormValidator;

@Controller
public class WalletController {
	
	@Autowired
	private WalletFormValidator walletFormValidator;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private BlockChainService chainService;

	@RequestMapping(value = "/wallet", method = RequestMethod.GET)
	public String createWalletView(Model model) {
		this.fillCreateModel(model);
		model.addAttribute("wallet", new Wallet());
		return "wallet/create";
	}
	
	@RequestMapping(value = "/wallet", method = RequestMethod.POST)
	public String createWallet(@Validated Wallet wallet, BindingResult result, Model model) {
		this.walletFormValidator.validate(wallet, result);
		if (result.hasErrors()) {
			this.fillCreateModel(model);
			return "wallet/create";
		}
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		wallet.setUser(user);
		this.walletService.createWallet(wallet);
		return "redirect:wallet";
	}
	
	@RequestMapping(value = "/wallet/directory", method = RequestMethod.GET)
	public String getWalletsList(Model model) {
		model.addAttribute("walletList", this.walletService.findAllWallets());
		return "wallet/directory";
	}
	
	/**
	 * Fills the model with the wallets which belong to the actual user.
	 * 
	 * @param model
	 * 			The model
	 */
	private void fillCreateModel(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		List<Wallet> wallets = this.chainService.updateFunds(user);
		model.addAttribute("walletList", wallets);
	}

}
