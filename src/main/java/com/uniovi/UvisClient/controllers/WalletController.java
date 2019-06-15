package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.UvisClientApplication;
import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.impl.BlockChainServiceImpl;
import com.uniovi.UvisClient.services.impl.WalletServiceImpl;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.util.DtoConverter;
import com.uniovi.UvisClient.validator.WalletFormValidator;

@Controller
public class WalletController {
	
	@Autowired
	private WalletFormValidator walletFormValidator;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private WalletServiceImpl walletService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private BlockChainServiceImpl chainService;

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
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		wallet.setUser(user);
		this.walletService.addWallet(wallet);
		this.chainService.send(DtoConverter.toDto(wallet), UvisClientApplication.initNode.getUrl(), new BlockChainSessionHandler(), "/app/chain/createWallet");
		return "redirect:wallet/list";
	}
	
	@RequestMapping(value = "/wallet/list", method = RequestMethod.GET)
	public String getWalletsList(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		model.addAttribute("walletList", user.getWallets());
		return "wallet/list";
	}

}
