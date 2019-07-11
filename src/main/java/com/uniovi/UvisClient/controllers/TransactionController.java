package com.uniovi.UvisClient.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Sender;
import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.entities.Wallet;
import com.uniovi.UvisClient.entities.dto.TransactionDto;
import com.uniovi.UvisClient.repositories.BlockChainRepository;
import com.uniovi.UvisClient.services.BlockChainService;
import com.uniovi.UvisClient.services.impl.UserServiceImpl;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.validator.TransactionFormValidator;

@Controller
public class TransactionController {
	
	public static final String LISTENER = "/app/chain/createTransaction";
	
	@Autowired 
	private UserServiceImpl userService;
	
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
	
	@RequestMapping(value = "/transaction/send", method = RequestMethod.GET)
	public String addTransactionView(@ModelAttribute("transaction") TransactionDto transaction, BindingResult result, Model model) {
		User user = this.userService.getUserByUsername(this.securityService.findLoggedInUsername());
		model.addAttribute("transaction", new TransactionDto());
		List<Wallet> wallets = this.chainService.updateFunds(user);
		List<Double> funds = wallets.stream().map(x -> x.getFunds()).collect(Collectors.toList());
		model.addAttribute("walletsList", wallets);
		model.addAttribute("funds", funds);
		return "transaction/create";
	}
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public String addTransaction(@ModelAttribute("transaction") TransactionDto transaction, BindingResult result, Model model) {
		this.transactionFormValidator.validate(transaction, result);
		if (result.hasErrors()) {
			return "redirect:transaction/send";
		}
		Sender sender = new Sender(transaction, BlockChainRepository.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), LISTENER);
		sender.start();
		return "redirect:transaction/list";
	}

}
