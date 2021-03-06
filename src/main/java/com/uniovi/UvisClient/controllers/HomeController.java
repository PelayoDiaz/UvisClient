package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.services.BlockChainService;

@Controller
public class HomeController {
		
	@Autowired
	private BlockChainService chainService;
	
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("conectedNodes", this.chainService.getNumberOfConnectedNodes());
		model.addAttribute("blockNumber", this.chainService.getNumberOfBlocks());
		model.addAttribute("processedTransactions", this.chainService.getTotalOfProcessedTransactions());
		model.addAttribute("pendingTransactions", this.chainService.getPendingTransactions().size());
		
		model.addAttribute("transactionList", this.chainService.getPendingTransactions());
		return "home";
	}
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public String error(Model model) {
		return "error";
	}
	
	
}
