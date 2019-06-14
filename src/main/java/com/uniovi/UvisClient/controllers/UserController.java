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
import com.uniovi.UvisClient.entities.dto.BlockChainDto;
import com.uniovi.UvisClient.services.BlockChainService;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.security.SecurityService;
import com.uniovi.UvisClient.validator.SignUpFormValidator;

@Controller
public class UserController {

	@Autowired
	private UserService usersService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private BlockChainService chainService;
	
	@Autowired 
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping("/")
	public String getIndex(Model model) {
		return "redirect:login";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		this.chainService.send(new BlockChainDto(), UvisClientApplication.initNode.getUrl(), new BlockChainSessionHandler(), "/app/chain/sendChain");
		return "login";
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	//public String signup(@ModelAttribute("user") User user, Model model) {
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, BindingResult result, Model model) {
		this.signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		usersService.addUser(user);
		securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
		return "redirect:home";
	}

}
