package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.UvisClientApplication;
import com.uniovi.UvisClient.communication.BlockChainSessionHandler;
import com.uniovi.UvisClient.entities.BlockChain;
import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.services.impl.BlockChainServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	private BlockChainServiceImpl chainService;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("conectedNodes", BlockChain.getInstance().getConectedNodes());
		model.addAttribute("walletList", BlockChain.getInstance().getWalletsList());
		model.addAttribute("transactionList", BlockChain.getInstance().getTransactionsList());
		return "home";
	}
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		return "redirect:login";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		this.chainService.send(new BlockChainDto(), UvisClientApplication.initNode.getUrl(), new BlockChainSessionHandler(), "/app/chain/sendChain");
		return "login";
	}
	
	
}
