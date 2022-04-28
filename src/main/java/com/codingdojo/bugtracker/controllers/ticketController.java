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

import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;
import com.codingdojo.bugtracker.services.ProjectService;
import com.codingdojo.bugtracker.services.TicketService;

@Controller
public class ticketController {
	@Autowired
	public TicketService ticketServ;
	@Autowired
	public ProjectService projectServ;
	
	@RequestMapping("/ticket/new")
	public String createTicketForm(
			HttpSession session,
			Model model) {
		
		//make sure user is logged in
		if(session.getAttribute("user_id") == null) {
			return "redirect:/login";
		}
		//add model for data binding
		model.addAttribute("newTicket", new Ticket());
		
		//retrieve all projects from database
		List<Project> allProjects = projectServ.getAllProjects();
		
		//send list to jsp
		model.addAttribute("projects", allProjects);
		
		//send enums
		model.addAttribute("types", TicketType.values());
		
		
		
		return "add.jsp";
	}
	
	@PostMapping("/ticket/new")
	public String createTicket(
			@Valid @ModelAttribute("newTicket") Ticket filledTicket,
			BindingResult result,
			Model model) {
		//Validate
		if(result.hasErrors()) {
			//retrieve all projects from database
			List<Project> allProjects = projectServ.getAllProjects();
			
			//send list to jsp
			model.addAttribute("projects", allProjects);
			
			//send enums
			model.addAttribute("types", TicketType.values());
			
			return "add.jsp";
		}
		else {
			ticketServ.createTicket(filledTicket);
			//WHERE I WANT THIS TO GO
			//return "redirect:/ticket/{ticket.id}
			
			//WHERE ITS GOING FOR NOW
			return "redirect:/";
		}
		
	}
	
}
