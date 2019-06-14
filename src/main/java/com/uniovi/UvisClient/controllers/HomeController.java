package com.uniovi.UvisClient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.entities.BlockChain;

@Controller
public class HomeController {

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("conectedNodes", BlockChain.getInstance().getConectedNodes());
		model.addAttribute("walletList", BlockChain.getInstance().getWalletsList());
		model.addAttribute("transactionList", BlockChain.getInstance().getTransactionsList());
		return "home";
	}
}
