package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Sender;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.repositories.BlockChainRepository;
import com.uniovi.UvisClient.services.BlockChainService;

@Controller
public class HomeController {
	
	public static final String LISTENER = "/app/chain/sendChain";
	
	@Autowired
	private BlockChainService chainService;
	
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("blockNumber", chainService.getNumberOfBlocks());
		model.addAttribute("conectedNodes", BlockChainRepository.getInstance().getConnectedNodes());
		model.addAttribute("walletList", BlockChainRepository.getInstance().getWalletsList());
		model.addAttribute("transactionList", BlockChainRepository.getInstance().getTransactionsList());
		return "home";
	}
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public synchronized String login(Model model) {
		Sender sender = new Sender(new BlockChainDto(), BlockChainRepository.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), LISTENER);
		sender.start();
		return "login";
	}
	
	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String error(Model model) {
		return "error";
	}
	
	
}
