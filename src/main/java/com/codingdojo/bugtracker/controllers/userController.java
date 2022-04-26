package com.codingdojo.bugtracker.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bugtracker.models.LoginUser;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.services.UserService;

@Controller
public class userController {
	@Autowired
	public UserService userServ;
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		return "dashboard.jsp";
	}
	
	@RequestMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("newLogin") LoginUser newLogin,
			BindingResult result,
			HttpSession session) {
		
		User user = userServ.login(newLogin, result);
		
		if(result.hasErrors()) {
			return "login.jsp";
		}
		
		//if no errors add to session
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_role", user.getRole());
		return "redirect:/";
		
	}
	
	@RequestMapping("/register")
	public String registrationForm(Model model) {
		model.addAttribute("newUser", new User());
		return "register.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result,
			HttpSession session) {
		
		User registeredUser = userServ.register(newUser, result);
		
		if(result.hasErrors()) {
			return "register.jsp";
		}
		
		//if no errors add to session
		else {
			session.setAttribute("user_id", registeredUser.getId());
			session.setAttribute("user_role", registeredUser.getRole());
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_id");
		session.removeAttribute("user_role");
		return "redirect:/login";
		
	}
}
