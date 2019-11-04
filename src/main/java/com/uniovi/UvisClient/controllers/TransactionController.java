package com.uniovi.UvisClient.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.services.BlockChainService;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.WalletService;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.validator.TransactionFormValidator;

@Controller
public class TransactionController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private WalletService walletService;
	
	@Autowired 
	private BlockChainService chainService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private TransactionFormValidator transactionFormValidator;

	@RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
	public String getTransactionsLists(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		
		model.addAttribute("pendingTransactionsList", this.chainService.getPendingTransactions(user));
		model.addAttribute("sentTransactionsList", this.chainService.getSentTransactions(user));
		model.addAttribute("receivedTransactionsList", this.chainService.getReceivedTransactions(user) );
		
		return "transaction/list";
	}
	
	@RequestMapping(value = "transaction", method = RequestMethod.GET)
	public String createWalletView(Model model) {
		this.fillCreateModel(model);
		model.addAttribute("transactionDto", new TransactionDto());
		return "transaction/create";
	}
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public String createTransaction(@Validated TransactionDto transactionDto, BindingResult result, Model model) {
		this.transactionFormValidator.validate(transactionDto, result);
		if (result.hasErrors()) {
			this.fillCreateModel(model);
			return "transaction/create";
		}
		this.walletService.sendFunds(transactionDto);
		return "redirect:transaction/list";
	}
	
	/**
	 * Fills the model with the params required.
	 * 
	 * @param model
	 * 			The model
	 */
	private void fillCreateModel(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		List<Wallet> wallets = this.chainService.updateFunds(user);
		List<Double> funds = wallets.stream().map(x -> x.getFunds()).collect(Collectors.toList());
		model.addAttribute("walletsList", wallets);
		model.addAttribute("funds", funds);
	}

}
