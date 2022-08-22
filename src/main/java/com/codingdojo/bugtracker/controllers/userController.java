package com.codingdojo.bugtracker.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bugtracker.models.LoginUser;
import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;
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
			
			//get Admin ticket status count
			int newTicketStatusCount = ticketServ.getAdminTicketStatusCount(TicketStatus.NEW);
			model.addAttribute("newTicketStatusCount", newTicketStatusCount);
			
			int openTicketStatusCount = ticketServ.getAdminTicketStatusCount(TicketStatus.OPEN);
			model.addAttribute("openTicketStatusCount", openTicketStatusCount);
			
			int inProgressTicketStatusCount = ticketServ.getAdminTicketStatusCount(TicketStatus.IN_PROGRESS);
			model.addAttribute("inProgressTicketStatusCount", inProgressTicketStatusCount);
			
			int resolvedTicketStatusCount = ticketServ.getAdminTicketStatusCount(TicketStatus.RESOLVED);
			model.addAttribute("resolvedTicketStatusCount", resolvedTicketStatusCount);
			
			int infoRequiredTicketStatusCount = ticketServ.getAdminTicketStatusCount(TicketStatus.INFO_REQUIRED);
			model.addAttribute("infoRequiredTicketStatusCount", infoRequiredTicketStatusCount);
			
			//get Admin ticket priority count
			int nonePriorityCount = ticketServ.getAdminTicketPriorityCount(0);
			model.addAttribute("nonePriorityCount", nonePriorityCount);
			
			int lowPriorityCount = ticketServ.getAdminTicketPriorityCount(1);
			model.addAttribute("lowPriorityCount", lowPriorityCount);
			
			int medPriorityCount = ticketServ.getAdminTicketPriorityCount(2);
			model.addAttribute("medPriorityCount", medPriorityCount);
			
			int highPriorityCount = ticketServ.getAdminTicketPriorityCount(3);
			model.addAttribute("highPriorityCount", highPriorityCount);
			
			//get Admin ticket type count
			int requestTypeCount = ticketServ.getAdminTicketTypeCount(TicketType.FEATURE_REQUESTS);
			model.addAttribute("requestTypeCount", requestTypeCount);
			
			int otherTypeCount = ticketServ.getAdminTicketTypeCount(TicketType.OTHER_COMMENTS);
			model.addAttribute("otherTypeCount", otherTypeCount);
			
			int bugTypeCount = ticketServ.getAdminTicketTypeCount(TicketType.BUGS_AND_ERRORS);
			model.addAttribute("bugTypeCount", bugTypeCount);
		}
		else if(session.getAttribute("user_role").equals(1)) {
			HashMap<String, Integer> counts = ticketServ.projectManagerCounts((Long) session.getAttribute("user_id"));
			
			
			model.addAttribute("newTicketStatusCount", counts.get("newTicket"));
			model.addAttribute("openTicketStatusCount", counts.get("openTicket"));
			model.addAttribute("inProgressTicketStatusCount", counts.get("inProgressTicket"));
			model.addAttribute("infoRequiredTicketStatusCount", counts.get("infoRequiredTicket"));
			model.addAttribute("resolvedTicketStatusCount", counts.get("resolvedTicket"));
			model.addAttribute("resolvedTicketCount", counts.get("resolvedTicket"));
			model.addAttribute("openTicketCount", counts.get("unresolvedTicket"));
			model.addAttribute("nonePriorityCount", counts.get("noPriorityTicket"));
			model.addAttribute("lowPriorityCount", counts.get("lowPriorityTicket"));
			model.addAttribute("medPriorityCount", counts.get("medPriorityTicket"));
			model.addAttribute("highPriorityCount", counts.get("highPriorityTicket"));
			model.addAttribute("requestTypeCount", counts.get("requestTypeTicket"));
			model.addAttribute("otherTypeCount", counts.get("otherTypeTicket"));
			model.addAttribute("bugTypeCount", counts.get("bugTypeTicket"));

		}
		else {
			//get unresolved ticket count and send to jsp
			int ticketCount = userServ.unresolvedAssignedTicketCount((Long) session.getAttribute("user_id"));
			model.addAttribute("openTicketCount", ticketCount);
			
			//get resolved ticket count and send to jsp
			int resolvedTicketCount = userServ.resolvedAssignedTicketCount((Long) session.getAttribute("user_id")); 
			model.addAttribute("resolvedTicketCount", resolvedTicketCount);
			
			//get Dev ticket status count
			int newTicketStatusCount = ticketServ.getDevTicketStatusCount(TicketStatus.NEW, (Long) session.getAttribute("user_id"));
			model.addAttribute("newTicketStatusCount", newTicketStatusCount);
			
			int openTicketStatusCount = ticketServ.getDevTicketStatusCount(TicketStatus.OPEN, (Long) session.getAttribute("user_id"));
			model.addAttribute("openTicketStatusCount", openTicketStatusCount);
			
			int inProgressTicketStatusCount = ticketServ.getDevTicketStatusCount(TicketStatus.IN_PROGRESS, (Long) session.getAttribute("user_id"));
			model.addAttribute("inProgressTicketStatusCount", inProgressTicketStatusCount);
			
			int resolvedTicketStatusCount = ticketServ.getDevTicketStatusCount(TicketStatus.RESOLVED, (Long) session.getAttribute("user_id"));
			model.addAttribute("resolvedTicketStatusCount", resolvedTicketStatusCount);
			
			int infoRequiredTicketStatusCount = ticketServ.getDevTicketStatusCount(TicketStatus.INFO_REQUIRED, (Long) session.getAttribute("user_id"));
			model.addAttribute("infoRequiredTicketStatusCount", infoRequiredTicketStatusCount);
			
			//get Dev ticket priority count
			int nonePriorityCount = ticketServ.getDevTicketPriorityCount(0, (Long) session.getAttribute("user_id"));
			model.addAttribute("nonePriorityCount", nonePriorityCount);
			
			int lowPriorityCount = ticketServ.getDevTicketPriorityCount(1, (Long) session.getAttribute("user_id"));
			model.addAttribute("lowPriorityCount", lowPriorityCount);
			
			int medPriorityCount = ticketServ.getDevTicketPriorityCount(2, (Long) session.getAttribute("user_id"));
			model.addAttribute("medPriorityCount", medPriorityCount);
			
			int highPriorityCount = ticketServ.getDevTicketPriorityCount(3, (Long) session.getAttribute("user_id"));
			model.addAttribute("highPriorityCount", highPriorityCount);
			
			//get Dev ticket type count
			int requestTypeCount = ticketServ.getDevTicketTypeCount(TicketType.FEATURE_REQUESTS, (Long) session.getAttribute("user_id"));
			model.addAttribute("requestTypeCount", requestTypeCount);
			
			int otherTypeCount = ticketServ.getDevTicketTypeCount(TicketType.OTHER_COMMENTS, (Long) session.getAttribute("user_id"));
			model.addAttribute("otherTypeCount", otherTypeCount);
			
			int bugTypeCount = ticketServ.getDevTicketTypeCount(TicketType.BUGS_AND_ERRORS, (Long) session.getAttribute("user_id"));
			model.addAttribute("bugTypeCount", bugTypeCount);
			 
		}
		//get count of tickets submitted by user that are still open
		int submittedTicketCount = ticketServ.ticketCount(TicketStatus.RESOLVED, (Long) session.getAttribute("user_id"));
		model.addAttribute("submittedTicketCount", submittedTicketCount);
		
		
		
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
	@RequestMapping("/login/demo/admin")
	public String loginAdmin(HttpSession session) {
		User user = userServ.loginAdmin();
		
		//add to session
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_role", user.getRole());
		session.setAttribute("user_name", user.getName());
		return "redirect:/";
	}
	
	@RequestMapping("/login/demo/pm")
	public String loginPM(HttpSession session) {
		User user = userServ.loginPM();
		
		//add to session
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_role", user.getRole());
		session.setAttribute("user_name", user.getName());
		return "redirect:/";
	}
	
	@RequestMapping("/login/demo/dev")
	public String loginDev(HttpSession session) {
		User user = userServ.loginDev();
		
		//add to session
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
	@RequestMapping("/user/roles")
	public String showRoles(Model model, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		if(!session.getAttribute("user_role").equals(0)) {
			return "redirect:/";
		}
		List<User> users = userServ.getAllUsers();
		model.addAttribute("users", users);
		return "management.jsp";
	}
	@RequestMapping("/user/{id}")
	public String editUser(
			@PathVariable("id") Long id,
			HttpSession session,
			Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		if(!session.getAttribute("user_role").equals(0)) {
			return "redirect:/";
		}
		User user = userServ.getOneUser(id);
		model.addAttribute("user", user);
		return "userEdit.jsp";
	}
	
	@PutMapping("/user/{id}")
	public String updateUser(
			@Valid @ModelAttribute("user") User filledUser,
			BindingResult result,
			@PathVariable("id") Long id) {
		//no validation needed
		//Update user
		User user = userServ.save(filledUser);
		
		return "redirect:/user/roles";
	}
}
