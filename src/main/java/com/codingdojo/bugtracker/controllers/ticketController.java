package com.codingdojo.bugtracker.controllers;

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

import com.codingdojo.bugtracker.models.Comment;
import com.codingdojo.bugtracker.models.Project;
import com.codingdojo.bugtracker.models.Ticket;
import com.codingdojo.bugtracker.models.Ticket.TicketStatus;
import com.codingdojo.bugtracker.models.Ticket.TicketType;
import com.codingdojo.bugtracker.models.User;
import com.codingdojo.bugtracker.services.CommentService;
import com.codingdojo.bugtracker.services.ProjectService;
import com.codingdojo.bugtracker.services.TicketService;
import com.codingdojo.bugtracker.services.UserService;

@Controller
public class ticketController {
	@Autowired
	public TicketService ticketServ;
	@Autowired
	public ProjectService projectServ;
	@Autowired
	public UserService userServ;
	@Autowired
	public CommentService commentServ;
	
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
	
	@RequestMapping("/tickets")
	public String showTickets(HttpSession session, Model model) {
		//make sure user is logged in
			if(session.getAttribute("user_id") == null) {
				return "redirect:/login";
			}
		//find user
		User user = userServ.getOneUser((Long) session.getAttribute("user_id"));
		//For admins find all tickets
		if(session.getAttribute("user_role").equals(0)) {
			List<Ticket> tickets = ticketServ.getAllTickets();
			model.addAttribute("tickets", tickets);
		}
		else if(session.getAttribute("user_role").equals(1)) {
			List<Ticket> tickets = userServ.allAssignedProjectsTickets((Long) session.getAttribute("user_id"));
			model.addAttribute("tickets", tickets);
		}
		else if(session.getAttribute("user_role").equals(2)) {
			List<Ticket> tickets = user.getAssignedTickets();
					model.addAttribute("tickets", tickets);
		}
		else {
			List<Ticket> tickets = ticketServ.getTicketBySubmitter((Long) session.getAttribute("user_id"));
			model.addAttribute("tickets", tickets);
		}
//		else {
//			List<Ticket> tickets = user.getSubmittingUsers();
//			model.addAttribute("tickets", tickets);
//		}
		
		return "tickets.jsp"; 
	}
	
	@RequestMapping("/ticket/{id}")
	public String showTicket(
			@PathVariable("id") Long id,
			HttpSession session,
			Model model) {
		//make sure user is logged in
			if(session.getAttribute("user_id") == null) {
				return "redirect:/login";
			}
			//find ticket
			Ticket ticket = ticketServ.getOneTicket(id);
			model.addAttribute("ticket", ticket);
			
			//send enums
			model.addAttribute("status", TicketStatus.values());
			
			//Send only dev and project manager users
			List<User> devs = userServ.getDevs();
			model.addAttribute("devs", devs);
			
			//send over all comments for that ticket
			List<Comment> comments = commentServ.getCommentsByTicket(id);
			model.addAttribute("comments", comments);
			
			model.addAttribute("newComment", new Comment());
			return "ticket.jsp";
	}
	
	@PutMapping("/ticket/{id}")
	public String updateTicket(
			@Valid @ModelAttribute("ticket") Ticket filledTicket,
			BindingResult result,
			@PathVariable("id") Long id) {
		
		//check status is no longer new
		if(filledTicket.getAssignedDev() != null && filledTicket.getTicketStatus() == TicketStatus.NEW) {
			filledTicket.setTicketStatus(TicketStatus.OPEN);
		}
		// no validation needed
		//preserve comments
		List<Comment> comments = commentServ.getCommentsByTicket(id);
		
		for(Comment comment : comments) {
			String text = comment.getCommentText();
			System.out.println(text);
		}
		
		Ticket ticket = ticketServ.createTicket(filledTicket);
		
		for(Comment comment : comments) {
			Comment newComment = new Comment();
			newComment.setCommentedTicket(ticket);
			newComment.setCommentingUser(comment.getCommentingUser());
			newComment.setCommentText(comment.getCommentText());
			
			
			commentServ.createComment(newComment);
		}
		return "redirect:/tickets";
	}
	
	@PostMapping("/comment/new")
	public String addComment(
			@Valid @ModelAttribute("comment") Comment filledComment,
			BindingResult result) {
		
		//no validation needed
		commentServ.createComment(filledComment);
		return "redirect:/ticket/"+filledComment.getCommentedTicket().getId();
	}
	
	
}
