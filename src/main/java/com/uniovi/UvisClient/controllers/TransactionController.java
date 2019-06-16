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
import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.services.BlockChainService;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.util.DtoConverter;
import com.uniovi.UvisClient.validator.TransactionFormValidator;

@Controller
public class TransactionController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private BlockChainService chainService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private TransactionFormValidator transactionFormValidator;

	@RequestMapping(value = "/transaction/list", method = RequestMethod.GET)
	public String getTransactionsLists(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		
		model.addAttribute("pendingTransactionsList", BlockChain.getInstance().getTransactionsList());
		model.addAttribute("sentTransactionsList", this.chainService.getSentTransactions(user));
		model.addAttribute("receivedTransactionsList", this.chainService.getReceivedTransactions(user) );
		
		return "transaction/list";
	}
	
	@RequestMapping(value = "/transaction/add", method = RequestMethod.GET)
	public String addTransactionView(Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		model.addAttribute("transaction", new TransactionDto());
		model.addAttribute("walletsList", user.getWallets());
		return "transaction/create";
	}
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public String addTransaction(@ModelAttribute("transaction") TransactionDto transaction, BindingResult result, Model model) {
		this.transactionFormValidator.validate(transaction, result);
		if (result.hasErrors()) {
			return "redirect:transaction/add";
		}
		this.chainService.send(transaction, UvisClientApplication.initNode.getUrl(), new BlockChainSessionHandler(), "/app/chain/createTransaction");
		return "redirect:transaction/list";
	}

}
