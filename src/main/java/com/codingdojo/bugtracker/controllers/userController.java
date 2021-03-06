package com.codingdojo.bugtracker.controllers;

import java.util.List;

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
import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.services.ProjectService;
import com.codingdojo.bugtracker.services.TicketService;
import com.codingdojo.bugtracker.services.UserService;

@Controller
public class userController {
	@Autowired
	public UserService userServ;
	@Autowired
	public ProjectService projectServ;
	@Autowired
	public TicketService ticketServ;
	
	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		//dashboard information for ADMINS and SUBMITTERS
		if(session.getAttribute("user_role").equals(0) || session.getAttribute("user_role").equals(3)) {
			//get Admin project count and send to jsp
			int adminProjectCount = projectServ.projectCount();
			model.addAttribute("projectCount", adminProjectCount);
			
			//get Admin open ticket count and send to jsp
			int adminTicketCount = ticketServ.ticketCount();
			model.addAttribute("openTicketCount", adminTicketCount);
			
			//get Admin new ticket count and send to jsp
			int adminUnassignedTicketCount = ticketServ.ticketCount(TicketStatus.NEW);
			model.addAttribute("unassignedTicketCount", adminUnassignedTicketCount);
			
			
		}
		else {
			//get project count and send to jsp
			int projectCount = userServ.countUsersProjects((Long) session.getAttribute("user_id"));
			model.addAttribute("projectCount", projectCount);
			
			//get unresolved ticket count and send to jsp
			int ticketCount = userServ.unresolvedAssignedTicketCount((Long) session.getAttribute("user_id"));
			model.addAttribute("openTicketCount", ticketCount);
			
			//get resolved ticket count and send to jsp
			int resolvedTicketCount = userServ.resolvedAssignedTicketCount((Long) session.getAttribute("user_id"));
			model.addAttribute("resolvedTicketCount", resolvedTicketCount);
			
		}
		//get count of tickets submitted by user that are still open
		int submittedTicketCount = ticketServ.ticketCount(TicketStatus.RESOLVED, (Long) session.getAttribute("user_id"));
		model.addAttribute("submittedTicketCount", submittedTicketCount);
		
		//get count of tickets by type
		//new
		int newCount = ticketServ.ticketCount(TicketStatus.NEW);
		model.addAttribute("newCount", newCount);
		//open
		int openCount = ticketServ.ticketCount(TicketStatus.OPEN);
		model.addAttribute("openCount", openCount);
		//in progress
		int inprogressCount = ticketServ.ticketCount(TicketStatus.IN_PROGRESS);
		model.addAttribute("inprogressCount", inprogressCount);
		//add info
		int addInfoCount = ticketServ.ticketCount(TicketStatus.INFO_REQUIRED);
		model.addAttribute("addInfoCount", addInfoCount);
		
		int totalCount = newCount + openCount + inprogressCount+ addInfoCount;
		model.addAttribute("totalCount", totalCount);
		
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
		session.setAttribute("user_name", user.getName());
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
			session.setAttribute("user_name", registeredUser.getName());
			return "redirect:/";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user_id");
		session.removeAttribute("user_role");
		session.removeAttribute("user_name");
		return "redirect:/login";
		
	}
}
