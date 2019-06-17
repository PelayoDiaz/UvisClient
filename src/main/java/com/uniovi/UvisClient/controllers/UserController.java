package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.UvisClient.entities.User;
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
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
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
