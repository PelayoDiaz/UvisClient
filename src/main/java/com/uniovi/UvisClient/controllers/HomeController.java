package com.uniovi.UvisClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.communication.Sender;
import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;

@Controller
public class HomeController {
	
	public static final String LISTENER = "/app/chain/sendChain";
	
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("conectedNodes", BlockChain.getInstance().getConnectedNodes());
		model.addAttribute("walletList", BlockChain.getInstance().getWalletsList());
		model.addAttribute("transactionList", BlockChain.getInstance().getTransactionsList());
		return "home";
	}
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		return "redirect:login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public synchronized String login(Model model) {
		Sender sender = new Sender(new BlockChainDto(), BlockChain.getInstance().getActualNode().getUrl(), new BlockChainSessionHandler(), LISTENER);
		sender.start();
		return "login";
	}
	
	
}
