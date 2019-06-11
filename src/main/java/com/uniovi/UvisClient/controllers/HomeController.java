package com.uniovi.UvisClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.UvisClient.entities.User;
import com.uniovi.UvisClient.services.MarksService;
import com.uniovi.UvisClient.services.UserService;
import com.uniovi.UvisClient.services.security.SecurityService;

@Controller
public class HomeController {

	@Autowired // Inyectar el servicio
	private MarksService marksService;

	@Autowired
	private UserService usersService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping("/")
	public String getList(Model model) {
		model.addAttribute("markList", marksService.getMarks());
		return "home";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signup(Model model) {
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, Model model) {
		usersService.addUser(user);
//		securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
		return "redirect:login";
	}

}
